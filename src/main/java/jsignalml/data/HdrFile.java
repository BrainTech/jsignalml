package jsignalml.data;

import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeFloat;
import jsignalml.TypeString;
import jsignalml.TypeList;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Properties;
import java.util.Map;
import java.util.Set;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

import jsignalml.util;
import jsignalml.logging.Logger;

public class HdrFile
	extends HashMap<String, Type> implements HdrInfo
{
	static Logger log = new Logger(HdrFile.class);

	public static final Map<String, Type> field_types
		= util.newHashMap();
	static {
		field_types.put("number_of_channels", TypeInt.I);
		field_types.put("number_of_samples", TypeInt.I);
		field_types.put("sampling_frequency", TypeFloat.I);
		field_types.put("channel_labels", TypeList.make(""));
		field_types.put("channel_types", TypeList.make(""));
		field_types.put("scaled", TypeInt.I);
		field_types.put("scaling_offset", TypeFloat.I);
		field_types.put("scaling_gain", TypeFloat.I);
	}

	public HdrFile(File file)
		throws FileNotFoundException,
		       IOException
	{
		Properties prop = new Properties();
		prop.load(new InputStreamReader(new FileInputStream(file)));

		for(String name: prop.stringPropertyNames()) {
			String value = (String) prop.get(name);

			Type type = field_types.get(name);
			if (type == null) {
				log.warn("%s: unknown field %s", file, name);
				type = TypeString.I;
			}

			super.put(name, convert(value, type));
		}
	}

	public Type convert(String value, Type type) {
		if (type instanceof TypeList) {
			String[] parts = value.split(",\\s*");
			return TypeList.make(parts);
		} else if (type instanceof TypeFloat) {
			return new TypeFloat(value);
		} else if (type instanceof TypeInt) {
			return new TypeInt(value);
		} else if (type instanceof TypeString) {
			return new TypeString(value);
		} else {
			throw new RuntimeException();
		}
	}
}