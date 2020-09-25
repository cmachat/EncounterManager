package application;


import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.DBConnection;

public class XMLImporter {
	private File input_file;
	private static final String[] sql_fields = {
			"pk_monster", "name", "size", "type", "source", "alignment", // 6
			"armor_class", "armor_type", // 8
			"hitpoints_average", "hitpoints_random", // 10
			"movement", // 11
			"stat_str", "stat_dex", "stat_con", "stat_int", "stat_wis", "stat_cha", // 17
			"saving_throws",
			"skills",
			"resistance", "dmg_immunities", "condition_immunities", "vulnerability",
			"senses",
			"passive_perception",
			"languages",
			"challenge",
			"traits",
			"actions",
			"legendary",
			"description"
	};
		
	private final HashMap<String, Integer> field_to_index;
	
	private final java.util.regex.Pattern
	re_uint, re_brackets, re_before_comma, re_after_comma;
	
	
	public XMLImporter(File file) throws Exception {
		input_file = file;
		
		field_to_index = new HashMap<String, Integer>(sql_fields.length);

		int field_idx = 1;
		for (String field : sql_fields) {
			if (field_to_index.containsKey(field))
				throw new Exception("Duplicate SQL parameter: " + field);
			field_to_index.put(field, field_idx++);
		}
		
		re_uint = Pattern.compile("^\\d+");
		re_brackets = Pattern.compile("\\((.*)\\)");
		re_before_comma = Pattern.compile("^(.*),?");
		re_after_comma = Pattern.compile(", (.*)");
	}
	
	
	public void import_to_db() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document doc = db.parse(input_file);

		NodeList nodes = doc.getElementsByTagName("compendium").item(0).getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++)
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				PreparedStatement stmt = prepare_statement();
				Element monster = (Element) nodes.item(i);
				stmt.setInt(field_idx("pk_monster"), i);

				String traits_str = "";
				String actions_str = "";
				String legendary_actions_str = "";
				String monster_name = "";

				try {

					NodeList props = monster.getChildNodes();
					for (int j = 0; j < props.getLength(); j++)
						if (props.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Element prop = (Element) props.item(j);
							String txt = prop.getTextContent();

							Matcher m_before = re_before_comma.matcher(txt);
							Matcher m_after = re_after_comma.matcher(txt);
							Matcher m_uint = re_uint.matcher(txt);
							Matcher m_brackets = re_brackets.matcher(txt);

							switch (prop.getNodeName()) {
							case "name":
								monster_name = txt;
								stmt.setString(field_idx("name"), txt);
								break;
							case "size":
								stmt.setString(field_idx("size"), txt);
								break;
							case "type":
								m_before.find();
								stmt.setString(field_idx("type"), m_before.group(1));
								if (m_after.find())
									stmt.setString(field_idx("source"), m_after.group(1));
								break;
							case "alignment":
								stmt.setString(field_idx("alignment"), txt);
								break;
							case "ac":
								m_uint.find();
								stmt.setInt(field_idx("armor_class"), Integer.parseUnsignedInt(m_uint.group()));
								if (m_brackets.find())
									stmt.setString(field_idx("armor_type"), m_brackets.group(1));
								break;
							case "hp":
								m_uint.find();
								stmt.setInt(field_idx("hitpoints_average"), Integer.parseUnsignedInt(m_uint.group()));
								if (m_brackets.find())
									stmt.setString(field_idx("hitpoints_random"), m_brackets.group(1));
								break;
							case "speed":
								stmt.setString(field_idx("movement"), txt);
								break;
							case "str":
								m_uint.find();
								stmt.setInt(field_idx("stat_str"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "dex":
								m_uint.find();
								stmt.setInt(field_idx("stat_dex"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "con":
								m_uint.find();
								stmt.setInt(field_idx("stat_con"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "int":
								m_uint.find();
								stmt.setInt(field_idx("stat_int"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "wis":
								m_uint.find();
								stmt.setInt(field_idx("stat_wis"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "cha":
								m_uint.find();
								stmt.setInt(field_idx("stat_cha"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "save":
								stmt.setString(field_idx("saving_throws"), txt);
								break;
							case "skill":
								stmt.setString(field_idx("skills"), txt);
								break;
							case "resist":
								stmt.setString(field_idx("resistance"), txt);
								break;
							case "immune":
								stmt.setString(field_idx("dmg_immunities"), txt);
								break;
							case "conditionImmune":
								stmt.setString(field_idx("condition_immunities"), txt);
								break;
							case "vulnerable":
								stmt.setString(field_idx("vulnerability"), txt);
								break;
							case "senses":
								stmt.setString(field_idx("senses"), txt);
								break;
							case "passive":
								m_uint.find();
								stmt.setInt(field_idx("passive_perception"), Integer.parseUnsignedInt(m_uint.group()));
								break;
							case "languages":
								stmt.setString(field_idx("languages"), txt);
								break;
							case "cr":
								stmt.setString(field_idx("challenge"), txt);
								break;
							case "trait": {
								if (!traits_str.equals(""))
									traits_str += "\r\n\r\n";
								traits_str += prop.getElementsByTagName("name").item(0).getTextContent();
								NodeList text_nodes = prop.getElementsByTagName("text");
								for (int k = 0; k < text_nodes.getLength(); k++)
									if (text_nodes.item(k).getNodeType() == Node.ELEMENT_NODE)
										traits_str += "\r\n" + text_nodes.item(k).getTextContent();
								break; }
							case "action": {
								if (!actions_str.equals(""))
									actions_str += "\r\n\r\n";
								actions_str += prop.getElementsByTagName("name").item(0).getTextContent();
								NodeList text_nodes = prop.getElementsByTagName("text");
								for (int k = 0; k < text_nodes.getLength(); k++)
									if (text_nodes.item(k).getNodeType() == Node.ELEMENT_NODE)
										actions_str += "\r\n" + text_nodes.item(k).getTextContent();
								if (prop.getElementsByTagName("attack").getLength() > 0)
									actions_str += "\r\n" + prop.getElementsByTagName("attack").item(0).getTextContent();
								break; }
							case "legendary": {
								if (!legendary_actions_str.equals(""))
									legendary_actions_str += "\r\n\r\n";
								legendary_actions_str += prop.getElementsByTagName("name").item(0).getTextContent();
								NodeList text_nodes = prop.getElementsByTagName("text");
								for (int k = 0; k < text_nodes.getLength(); k++)
									if (text_nodes.item(k).getNodeType() == Node.ELEMENT_NODE)
										legendary_actions_str += "\r\n" + text_nodes.item(k).getTextContent();
								break; }
							case "description":
								stmt.setString(field_idx("description"), txt);
								break;
							default:
								System.err.println("WARNING: Ignoring unknown XML node " + prop);
							}
						}

					stmt.setString(field_idx("traits"), traits_str);
					stmt.setString(field_idx("actions"), actions_str);
					stmt.setString(field_idx("legendary"), legendary_actions_str);

					DBConnection.executePreparedStatement(stmt);
				} catch (Exception e) {
					System.err.println(monster_name + ":");
					e.printStackTrace();
					throw e;
				}
			}
	}


	private PreparedStatement prepare_statement() throws SQLException {
		StringBuilder fields_str = new StringBuilder("(");
		StringBuilder args_str = new StringBuilder("(");

		for (String field : sql_fields) {
			fields_str.append("`" + field + "`, ");
			args_str.append("?, ");
		}
		fields_str.replace(fields_str.length() - 2, fields_str.length(), ")");
		args_str.replace(args_str.length() - 2, args_str.length(), ")");

		PreparedStatement rv = DBConnection.prepareStatement("INSERT INTO `monster` " + fields_str + " VALUES " + args_str);
		
		rv.setString(field_idx("saving_throws"), "who knows?");
		rv.setString(field_idx("source"), "unknown");
		rv.setString(field_idx("armor_type"), "");
		rv.setString(field_idx("hitpoints_random"), "your problem");
		rv.setString(field_idx("resistance"), "none");
		rv.setString(field_idx("skills"), "none");
		rv.setString(field_idx("dmg_immunities"), "none");
		rv.setString(field_idx("condition_immunities"), "none");
		rv.setString(field_idx("vulnerability"), "none");
		rv.setString(field_idx("senses"), "none");
		rv.setString(field_idx("languages"), "Blarglish");
		rv.setString(field_idx("passive_perception"), "stupid");
		rv.setString(field_idx("challenge"), "idiot");
		rv.setString(field_idx("traits"), "");
		rv.setString(field_idx("actions"), "");
		rv.setString(field_idx("legendary"), "");
		rv.setString(field_idx("description"), "");
		
		return rv;
	}


	private int field_idx(String name)
	{ return field_to_index.get(name); }


}




















