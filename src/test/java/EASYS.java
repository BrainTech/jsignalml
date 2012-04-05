import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;
import jsignalml.BitForm;
import jsignalml.Builtins;
import jsignalml.ContextDumper;
import jsignalml.MyBuffer;
import jsignalml.Type;
import jsignalml.TypeFloat;
import jsignalml.TypeInt;
import jsignalml.TypeList;
import jsignalml.TypeMap;
import jsignalml.TypeString;
import jsignalml.codec.ChannelClass;
import jsignalml.codec.ChannelSetClass;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.Context;
import jsignalml.codec.FunctionParam;
import jsignalml.codec.OuterLoopClass;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.logging.Logger;
import org.apache.log4j.BasicConfigurator;


/**
 * 
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:171)
 * 
 */
public class EASYS
    extends Signalml
{

    final static Logger log = new Logger(EASYS.class);
    EASYS.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        log.debug("EASYS.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            EASYS.File_main obj = get_main();
            register("main", obj);
            obj.createParams();
        }
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        log.debug("EASYS.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            EASYS.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public java.lang.String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:179)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
        return "EASYS";
    }

    public static void main(java.lang.String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        BasicConfigurator.configure();
        EASYS reader = new EASYS();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        System.out.print(ContextDumper.dump(reader));
        for (int i = 1; (i<args.length); i ++) {
            long count = reader.get_set().getNumberOfSamples();
            FloatBuffer buffer = FloatBuffer.allocate(((int) count));
            reader.get_set().getChannel(Integer.decode(args[i])).getSamples(buffer, 0);
        }
    }

    public File getCurrentFilename() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:181)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:353)
        return null;
    }

    public java.lang.String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:182)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:362)
        return null;
    }

    public java.lang.String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:371)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:343)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:380)
    }

    public EASYS.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:821)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
        if (get_main == null) {
            get_main = new EASYS.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:767)
     * parent paramClass=_param_mapping
     * parent paramClass=_param_signature
     * parent paramClass=_param_magic
     * parent paramClass=_param_file_type
     * parent paramClass=_param_number_of_channels
     * parent paramClass=_param_sample_unit
     * parent paramClass=_param_number_of_auxiliary_channels
     * parent paramClass=_param_sampling_frequency
     * parent paramClass=_param_number_of_samples
     * parent paramClass=_param_data_validation_field
     * parent paramClass=_param_data_cell_size
     * parent paramClass=_param_calibration_gain
     * parent paramClass=_param__calibration_unit
     * parent paramClass=_param_calibration_offset
     * parent paramClass=_param_data_org
     * parent paramClass=_param_data_offset
     * parent paramClass=_param_xhdr_org
     * parent paramClass=_param_extended_header_offset
     * parent paramClass=_param_record_name_map
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        EASYS.File_main._param_mapping get_mapping = null;
        EASYS.File_main._param_signature get_signature = null;
        EASYS.File_main._param_magic get_magic = null;
        EASYS.File_main._param_file_type get_file_type = null;
        EASYS.File_main._param_number_of_channels get_number_of_channels = null;
        EASYS.File_main._param_sample_unit get_sample_unit = null;
        EASYS.File_main._param_number_of_auxiliary_channels get_number_of_auxiliary_channels = null;
        EASYS.File_main._param_sampling_frequency get_sampling_frequency = null;
        EASYS.File_main._param_number_of_samples get_number_of_samples = null;
        EASYS.File_main._param_data_validation_field get_data_validation_field = null;
        EASYS.File_main._param_data_cell_size get_data_cell_size = null;
        EASYS.File_main._param_calibration_gain get_calibration_gain = null;
        EASYS.File_main._param__calibration_unit get__calibration_unit = null;
        EASYS.File_main._param_calibration_offset get_calibration_offset = null;
        EASYS.File_main._param_data_org get_data_org = null;
        EASYS.File_main._param_data_offset get_data_offset = null;
        EASYS.File_main._param_xhdr_org get_xhdr_org = null;
        EASYS.File_main._param_extended_header_offset get_extended_header_offset = null;
        EASYS.File_main._param_record_name_map get_record_name_map = null;
        EASYS.File_main.ChannelSet_data get_data = null;

        public File_main() {
        }

        public Type access(java.lang.String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:803)
            return super.access(name);
        }

        public void register(java.lang.String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:811)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("File_main.createParams()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("mapping", get_mapping());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("signature", get_signature());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("magic", get_magic());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("file_type", get_file_type());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("number_of_channels", get_number_of_channels());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("sample_unit", get_sample_unit());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("number_of_auxiliary_channels", get_number_of_auxiliary_channels());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("sampling_frequency", get_sampling_frequency());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("number_of_samples", get_number_of_samples());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("data_validation_field", get_data_validation_field());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("data_cell_size", get_data_cell_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("calibration_gain", get_calibration_gain());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("_calibration_unit", get__calibration_unit());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("calibration_offset", get_calibration_offset());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("data_org", get_data_org());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("data_offset", get_data_offset());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("xhdr_org", get_xhdr_org());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("extended_header_offset", get_extended_header_offset());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("record_name_map", get_record_name_map());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1248)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                EASYS.File_main.ChannelSet_data obj = get_data();
                register("data", obj);
                obj.createParams();
            }
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("File_main.createChannels()");
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1248)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                EASYS.File_main.ChannelSet_data obj = get_data();
                obj.createChannels();
            }
            registerChannelSet(get_data());
        }

        public java.lang.String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:753)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
            return "main";
        }

        public EASYS.File_main._param_mapping get_mapping() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_mapping == null) {
                get_mapping = new EASYS.File_main._param_mapping();
            }
            return get_mapping;
        }

        public EASYS.File_main._param_signature get_signature() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_signature == null) {
                get_signature = new EASYS.File_main._param_signature();
            }
            return get_signature;
        }

        public EASYS.File_main._param_magic get_magic() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_magic == null) {
                get_magic = new EASYS.File_main._param_magic();
            }
            return get_magic;
        }

        public EASYS.File_main._param_file_type get_file_type() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_file_type == null) {
                get_file_type = new EASYS.File_main._param_file_type();
            }
            return get_file_type;
        }

        public EASYS.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_number_of_channels == null) {
                get_number_of_channels = new EASYS.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public EASYS.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_sample_unit == null) {
                get_sample_unit = new EASYS.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public EASYS.File_main._param_number_of_auxiliary_channels get_number_of_auxiliary_channels() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_number_of_auxiliary_channels == null) {
                get_number_of_auxiliary_channels = new EASYS.File_main._param_number_of_auxiliary_channels();
            }
            return get_number_of_auxiliary_channels;
        }

        public EASYS.File_main._param_sampling_frequency get_sampling_frequency() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_sampling_frequency == null) {
                get_sampling_frequency = new EASYS.File_main._param_sampling_frequency();
            }
            return get_sampling_frequency;
        }

        public EASYS.File_main._param_number_of_samples get_number_of_samples() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_number_of_samples == null) {
                get_number_of_samples = new EASYS.File_main._param_number_of_samples();
            }
            return get_number_of_samples;
        }

        public EASYS.File_main._param_data_validation_field get_data_validation_field() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data_validation_field == null) {
                get_data_validation_field = new EASYS.File_main._param_data_validation_field();
            }
            return get_data_validation_field;
        }

        public EASYS.File_main._param_data_cell_size get_data_cell_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data_cell_size == null) {
                get_data_cell_size = new EASYS.File_main._param_data_cell_size();
            }
            return get_data_cell_size;
        }

        public EASYS.File_main._param_calibration_gain get_calibration_gain() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_calibration_gain == null) {
                get_calibration_gain = new EASYS.File_main._param_calibration_gain();
            }
            return get_calibration_gain;
        }

        public EASYS.File_main._param__calibration_unit get__calibration_unit() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get__calibration_unit == null) {
                get__calibration_unit = new EASYS.File_main._param__calibration_unit();
            }
            return get__calibration_unit;
        }

        public EASYS.File_main._param_calibration_offset get_calibration_offset() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_calibration_offset == null) {
                get_calibration_offset = new EASYS.File_main._param_calibration_offset();
            }
            return get_calibration_offset;
        }

        public EASYS.File_main._param_data_org get_data_org() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data_org == null) {
                get_data_org = new EASYS.File_main._param_data_org();
            }
            return get_data_org;
        }

        public EASYS.File_main._param_data_offset get_data_offset() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data_offset == null) {
                get_data_offset = new EASYS.File_main._param_data_offset();
            }
            return get_data_offset;
        }

        public EASYS.File_main._param_xhdr_org get_xhdr_org() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_xhdr_org == null) {
                get_xhdr_org = new EASYS.File_main._param_xhdr_org();
            }
            return get_xhdr_org;
        }

        public EASYS.File_main._param_extended_header_offset get_extended_header_offset() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_extended_header_offset == null) {
                get_extended_header_offset = new EASYS.File_main._param_extended_header_offset();
            }
            return get_extended_header_offset;
        }

        public EASYS.File_main._param_record_name_map get_record_name_map() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_record_name_map == null) {
                get_record_name_map = new EASYS.File_main._param_record_name_map();
            }
            return get_record_name_map;
        }

        public EASYS.File_main.ChannelSet_data get_data() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1245)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data == null) {
                get_data = new EASYS.File_main.ChannelSet_data();
            }
            return get_data;
        }


        /**
         * 
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
         * jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1240)
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1226)
         * 
         */
        public class ChannelSet_data
            extends ChannelSetClass
        {

            EASYS.File_main.ChannelSet_data.Loop_extended_header get_extended_header = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1242)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                log.debug("ChannelSet_data.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                    EASYS.File_main.ChannelSet_data.Loop_extended_header obj = get_extended_header();
                    register("extended_header", obj);
                    obj.createParams();
                }
            }

            public void createChannels() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1242)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                log.debug("ChannelSet_data.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                    EASYS.File_main.ChannelSet_data.Loop_extended_header obj = get_extended_header();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public java.lang.String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1227)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data";
            }

            public EASYS.File_main.ChannelSet_data.Loop_extended_header get_extended_header() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:952)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_extended_header == null) {
                    get_extended_header = new EASYS.File_main.ChannelSet_data.Loop_extended_header();
                }
                return get_extended_header;
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:953)
            }


            /**
             * 
             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:928)
             * 
             */
            public class Loop_extended_header
                extends OuterLoopClass
            {


                public void createParams() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                    log.debug("Loop_extended_header.createParams()");
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                    log.debug("Loop_extended_header.createChannels()");
                }

                public java.lang.String id() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:929)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                    return "extended_header";
                }

                protected TypeList getSequence() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:930)
                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:965)
                    TypeList range = ((TypeList)(get_xhdr_org().get().isTrue()?Builtins.range().call(new TypeInt(20)):new TypeList()));
                    return range;
                }

                protected EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1193)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:982)
                    return new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner(((TypeInt) index));
                }


                /**
                 * 
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1001)
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:933)
                 * 
                 */
                public class extended_header_inner
                    extends OuterLoopClass.LoopClass
                {

                    final EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr index;
                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr get_if_ext_hdr = null;

                    extended_header_inner(TypeInt indexHdr) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1191)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1211)
                        this.index = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr(indexHdr);
                    }

                    public void createParams() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                        log.debug("extended_header_inner.createParams()");
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:918)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                        register("indexHdr", this.index);
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr obj = get_if_ext_hdr();
                            register("if_ext_hdr", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                        log.debug("extended_header_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr obj = get_if_ext_hdr();
                            obj.createChannels();
                        }
                    }

                    public java.lang.String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:934)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                        return "extended_header";
                    }

                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr get_indexHdr() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:914)
                        return this.index;
                    }

                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr get_if_ext_hdr() {
                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1043)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_if_ext_hdr == null) {
                            get_if_ext_hdr = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr();
                        }
                        return get_if_ext_hdr;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1038)
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1023)
                     * parent paramClass=_param_offset
                     * parent paramClass=_param_record_mnemonic
                     * parent paramClass=_param_record_size
                     * 
                     */
                    public class If_if_ext_hdr
                        extends ConditionalClass
                    {

                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_offset get_offset = null;
                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_mnemonic get_record_mnemonic = null;
                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_size get_record_size = null;
                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names get_if_record_channel_names = null;

                        public void createParamsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createParamsIf()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            register("offset", get_offset());
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            register("record_mnemonic", get_record_mnemonic());
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            register("record_size", get_record_size());
                            {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names obj = get_if_record_channel_names();
                                register("if_record_channel_names", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createChannelsIf()");
                            {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names obj = get_if_record_channel_names();
                                obj.createChannels();
                            }
                        }

                        public void createParamsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createParamsElseIf()");
                        }

                        public void createChannelsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createChannelsElseIf()");
                        }

                        public void createParamsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createParamsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1093)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1();
                                register("gen_id_1", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                            log.debug("If_if_ext_hdr.createChannelsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1093)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1();
                                obj.createChannels();
                            }
                        }

                        public boolean hasElseIf() {
                            return false;
                        }

                        public java.lang.String id() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "if_ext_hdr";
                        }

                        public Type getCondition() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1025)
                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1064)
                            Type test = (((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False).isTrue()?((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False):get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("record_size"));
                            return test;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_offset get_offset() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_offset == null) {
                                get_offset = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_offset();
                            }
                            return get_offset;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_mnemonic get_record_mnemonic() {
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_record_mnemonic == null) {
                                get_record_mnemonic = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_mnemonic();
                            }
                            return get_record_mnemonic;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_size get_record_size() {
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_record_size == null) {
                                get_record_size = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_size();
                            }
                            return get_record_size;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names get_if_record_channel_names() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1043)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_if_record_channel_names == null) {
                                get_if_record_channel_names = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names();
                            }
                            return get_if_record_channel_names;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                         * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1095)
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1078)
                         * parent paramClass=_param_record_size
                         * 
                         */
                        public class Else_gen_id_1
                            extends ConditionalClass.ElseBranchClass
                        {

                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size get_record_size = null;

                            public void createParams() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1096)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("Else_gen_id_1.createParams()");
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                register("record_size", get_record_size());
                            }

                            public void createChannels() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1096)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("Else_gen_id_1.createChannels()");
                            }

                            public java.lang.String id() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1079)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "gen_id_1";
                            }

                            public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size get_record_size() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                if (get_record_size == null) {
                                    get_record_size = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size();
                                }
                                return get_record_size;
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                             * node.type=TypeInt
                             * --> nodetype=TypeInt
                             * 
                             */
                            public class _param_record_size
                                extends Param<TypeInt>
                            {

                                Long get_p = null;

                                public java.lang.String id() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                    return "record_size";
                                }

                                protected TypeInt _get() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                    // node.type=TypeInt
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return ((TypeInt) new TypeInt(0));
                                }

                                public Long get_p() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                                    // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                    if (get_p == null) {
                                        get_p = this.get().safeLongValue();
                                    }
                                    return get_p;
                                }

                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1038)
                         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1023)
                         * 
                         */
                        public class If_if_record_channel_names
                            extends ConditionalClass
                        {

                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels get_channels = null;

                            public void createParamsIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createParamsIf()");
                                {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels obj = get_channels();
                                    register("channels", obj);
                                    obj.createParams();
                                }
                            }

                            public void createChannelsIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createChannelsIf()");
                                {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels obj = get_channels();
                                    obj.createChannels();
                                    obj.createLoopChannels();
                                }
                            }

                            public void createParamsElseIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createParamsElseIf()");
                            }

                            public void createChannelsElseIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createChannelsElseIf()");
                            }

                            public void createParamsElse() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createParamsElse()");
                            }

                            public void createChannelsElse() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                log.debug("If_if_record_channel_names.createChannelsElse()");
                            }

                            public boolean hasElseIf() {
                                return false;
                            }

                            public java.lang.String id() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "if_record_channel_names";
                            }

                            public Type getCondition() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1025)
                                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1064)
                                Type test = ((get_record_mnemonic().get().compareTo(new TypeString("CN")) == 0)?TypeInt.True:TypeInt.False);
                                return test;
                            }

                            public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels get_channels() {
                                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:952)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                if (get_channels == null) {
                                    get_channels = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels();
                                }
                                return get_channels;
                                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:953)
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:928)
                             * 
                             */
                            public class Loop_channels
                                extends OuterLoopClass
                            {


                                public void createParams() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                    log.debug("Loop_channels.createParams()");
                                }

                                public void createChannels() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                    log.debug("Loop_channels.createChannels()");
                                }

                                public java.lang.String id() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:929)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                    return "channels";
                                }

                                protected TypeList getSequence() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:930)
                                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:965)
                                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                                    return range;
                                }

                                protected EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner createLoop(Type index) {
                                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1193)
                                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:982)
                                    return new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner(((TypeInt) index));
                                }


                                /**
                                 * 
                                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1001)
                                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:933)
                                 * parent paramClass=_param_channel_name_ASCIIZ
                                 * parent paramClass=_param_channel_name_ASCII
                                 * parent paramClass=_param_channel_name
                                 * parent paramClass=_param_mapping
                                 * 
                                 */
                                public class channels_inner
                                    extends OuterLoopClass.LoopClass
                                {

                                    final EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index index;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0 = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ get_channel_name_ASCIIZ = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCII get_channel_name_ASCII = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf get_szukajIf = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get_mapping = null;

                                    channels_inner(TypeInt index) {
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1191)
                                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1211)
                                        this.index = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index(index);
                                    }

                                    public void createParams() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        log.debug("channels_inner.createParams()");
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:918)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        register("index", this.index);
                                        {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1295)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                                            register("gen_id_0", obj);
                                            obj.createParams();
                                        }
                                        // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        register("channel_name_ASCIIZ", get_channel_name_ASCIIZ());
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        register("channel_name_ASCII", get_channel_name_ASCII());
                                        {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf obj = get_szukajIf();
                                            register("szukajIf", obj);
                                            obj.createParams();
                                        }
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        register("channel_name", get_channel_name());
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        register("mapping", get_mapping());
                                    }

                                    public void createChannels() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                        log.debug("channels_inner.createChannels()");
                                        {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1295)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                                            obj.createChannels();
                                        }
                                        registerChannel(get_gen_id_0());
                                        {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1051)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf obj = get_szukajIf();
                                            obj.createChannels();
                                        }
                                    }

                                    public java.lang.String id() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:934)
                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                        return "channels";
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index get_index() {
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:914)
                                        return this.index;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0() {
                                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1292)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_gen_id_0 == null) {
                                            get_gen_id_0 = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0();
                                        }
                                        return get_gen_id_0;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ get_channel_name_ASCIIZ() {
                                        // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_channel_name_ASCIIZ == null) {
                                            get_channel_name_ASCIIZ = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ();
                                        }
                                        return get_channel_name_ASCIIZ;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCII get_channel_name_ASCII() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_channel_name_ASCII == null) {
                                            get_channel_name_ASCII = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCII();
                                        }
                                        return get_channel_name_ASCII;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf get_szukajIf() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1043)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_szukajIf == null) {
                                            get_szukajIf = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf();
                                        }
                                        return get_szukajIf;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_channel_name == null) {
                                            get_channel_name = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name();
                                        }
                                        return get_channel_name;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get_mapping() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                        if (get_mapping == null) {
                                            get_mapping = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping();
                                        }
                                        return get_mapping;
                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1287)
                                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1260)
                                     * 
                                     */
                                    public class Channel_gen_id_0
                                        extends ChannelClass
                                    {


                                        public void createParams() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1289)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("Channel_gen_id_0.createParams()");
                                        }

                                        public void createChannels() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1289)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("Channel_gen_id_0.createChannels()");
                                        }

                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1262)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "gen_id_0";
                                        }

                                        protected MyBuffer _buffer() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1263)
                                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1304)
                                            return buffer();
                                        }

                                        public TypeString getSampleFormat() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1264)
                                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1316)
                                            // node.format.type=TypeString
                                            TypeString value = new TypeString("<i2");
                                            return ((TypeString) value);
                                        }

                                        public TypeInt mapSample(long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1265)
                                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1329)
                                            Type value = get_mapping().get();
                                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                                        }

                                        public float getSample(long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1266)
                                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1345)
                                            TypeString format_ = this.getSampleFormat();
                                            BitForm format = BitForm.get(format_);
                                            ByteBuffer buffer = _buffer().source;
                                            float calibGain = getCalibrationGain().getValue().floatValue();
                                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                                            float sampleUnit = getSampleUnit().getValue().floatValue();
                                            float value = format.read(buffer, ((int) get_mapping().call_p(sample)));
                                            return (((value-calibOffs)*calibGain)*sampleUnit);
                                        }

                                        public void getSamples(FloatBuffer dst, long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1267)
                                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1418)
                                            TypeString format_ = this.getSampleFormat();
                                            BitForm format = BitForm.get(format_);
                                            ByteBuffer buffer = _buffer().source;
                                            float calibGain = getCalibrationGain().getValue().floatValue();
                                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                                            float sampleUnit = getSampleUnit().getValue().floatValue();
                                            int count = dst.remaining();
                                            while (count--> 0) {
                                                float value = format.read(buffer, ((int) get_mapping().call_p(sample ++)));
                                                dst.put((((value-calibOffs)*calibGain)*sampleUnit));
                                            }
                                        }

                                        public double getSamplingFrequency() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1268)
                                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1496)
                                            Type value = get_sampling_frequency().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast.getValue();
                                        }

                                        public long getNumberOfSamples() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1269)
                                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1509)
                                            Type value = get_number_of_samples().get();
                                            TypeInt cast = TypeInt.I.make(value);
                                            return cast.safeLongValue();
                                        }

                                        public java.lang.String getChannelName() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1270)
                                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1535)
                                            Type value = get_channel_name().get();
                                            TypeString stringValue = ((TypeString) value);
                                            return stringValue.getValue();
                                        }

                                        public TypeFloat getCalibrationGain() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1271)
                                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1550)
                                            Type value = get_calibration_gain().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                        public TypeFloat getCalibrationOffset() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1272)
                                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1572)
                                            Type value = get_calibration_offset().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                        public TypeFloat getSampleUnit() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1273)
                                            // jsignalml.JavaClassGen.getSampleUnitMethod(JavaClassGen.java:1523)
                                            Type value = get_sample_unit().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1038)
                                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1023)
                                     * parent paramClass=_param_channel_name_temp
                                     * 
                                     */
                                    public class If_szukajIf
                                        extends ConditionalClass
                                    {

                                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf._param_channel_name_temp get_channel_name_temp = null;
                                        EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf get_szukajElseIf = null;

                                        public void createParamsIf() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createParamsIf()");
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            register("channel_name_temp", get_channel_name_temp());
                                        }

                                        public void createChannelsIf() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createChannelsIf()");
                                        }

                                        public void createParamsElseIf() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createParamsElseIf()");
                                            {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1150)
                                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf obj = get_szukajElseIf();
                                                register("szukajElseIf", obj);
                                                obj.createParams();
                                            }
                                        }

                                        public void createChannelsElseIf() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createChannelsElseIf()");
                                            {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1150)
                                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf obj = get_szukajElseIf();
                                                obj.createChannels();
                                            }
                                        }

                                        public void createParamsElse() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createParamsElse()");
                                        }

                                        public void createChannelsElse() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1022)
                                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                            log.debug("If_szukajIf.createChannelsElse()");
                                        }

                                        public boolean hasElseIf() {
                                            return true;
                                        }

                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "szukajIf";
                                        }

                                        public Type getCondition() {
                                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:576)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1025)
                                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1064)
                                            Type test = ((get_channel_name_ASCII().get().compareTo(new TypeString("F7")) == 0)?TypeInt.True:TypeInt.False);
                                            return test;
                                        }

                                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf._param_channel_name_temp get_channel_name_temp() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                            if (get_channel_name_temp == null) {
                                                get_channel_name_temp = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf._param_channel_name_temp();
                                            }
                                            return get_channel_name_temp;
                                        }

                                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf get_szukajElseIf() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1146)
                                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                            if (get_szukajElseIf == null) {
                                                get_szukajElseIf = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf();
                                            }
                                            return get_szukajElseIf;
                                        }


                                        /**
                                         * 
                                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                         * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1138)
                                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                                         * parent paramClass=_param_channel_name_temp
                                         * 
                                         */
                                        public class ElseIf_szukajElseIf
                                            extends ConditionalClass.ElseIfBranchClass
                                        {

                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf._param_channel_name_temp get_channel_name_temp = null;

                                            public boolean hasElseIf() {
                                                return false;
                                            }

                                            public void createParamsIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createParamsIf()");
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                register("channel_name_temp", get_channel_name_temp());
                                            }

                                            public void createChannelsIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createChannelsIf()");
                                            }

                                            public void createParamsElseIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createParamsElseIf()");
                                            }

                                            public void createChannelsElseIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createChannelsElseIf()");
                                            }

                                            public void createParamsElse() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createParamsElse()");
                                                {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1093)
                                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse();
                                                    register("szukajElse", obj);
                                                    obj.createParams();
                                                }
                                            }

                                            public void createChannelsElse() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1143)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                log.debug("ElseIf_szukajElseIf.createChannelsElse()");
                                                {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1093)
                                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse();
                                                    obj.createChannels();
                                                }
                                            }

                                            public java.lang.String id() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1124)
                                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                                return "szukajElseIf";
                                            }

                                            public Type getCondition() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:616)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1125)
                                                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1163)
                                                Type test = ((get_channel_name_ASCII().get().compareTo(new TypeString("F8")) == 0)?TypeInt.True:TypeInt.False);
                                                return test;
                                            }

                                            public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf._param_channel_name_temp get_channel_name_temp() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                                if (get_channel_name_temp == null) {
                                                    get_channel_name_temp = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf._param_channel_name_temp();
                                                }
                                                return get_channel_name_temp;
                                            }


                                            /**
                                             * 
                                             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                             * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1095)
                                             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1078)
                                             * parent paramClass=_param_channel_name_temp
                                             * 
                                             */
                                            public class Else_szukajElse
                                                extends ConditionalClass.ElseBranchClass
                                            {

                                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse._param_channel_name_temp get_channel_name_temp = null;

                                                public void createParams() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1096)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                    log.debug("Else_szukajElse.createParams()");
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                                                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                    register("channel_name_temp", get_channel_name_temp());
                                                }

                                                public void createChannels() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1077)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1096)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                                                    log.debug("Else_szukajElse.createChannels()");
                                                }

                                                public java.lang.String id() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:649)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1079)
                                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                                    return "szukajElse";
                                                }

                                                public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse._param_channel_name_temp get_channel_name_temp() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                                                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                                    if (get_channel_name_temp == null) {
                                                        get_channel_name_temp = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.If_szukajIf.ElseIf_szukajElseIf.Else_szukajElse._param_channel_name_temp();
                                                    }
                                                    return get_channel_name_temp;
                                                }


                                                /**
                                                 * 
                                                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                                 * node.type=TypeString
                                                 * --> nodetype=TypeString
                                                 * 
                                                 */
                                                public class _param_channel_name_temp
                                                    extends Param<TypeString>
                                                {


                                                    public java.lang.String id() {
                                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                                        return "channel_name_temp";
                                                    }

                                                    protected TypeString _get() {
                                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                                        // node.type=TypeString
                                                        // node.expr.type=TypeString
                                                        // --> nodetype=TypeString
                                                        return ((TypeString) get_channel_name_ASCII().get().add(new TypeString("_inne")));
                                                    }

                                                }

                                            }


                                            /**
                                             * 
                                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                             * node.type=TypeString
                                             * --> nodetype=TypeString
                                             * 
                                             */
                                            public class _param_channel_name_temp
                                                extends Param<TypeString>
                                            {


                                                public java.lang.String id() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                                    return "channel_name_temp";
                                                }

                                                protected TypeString _get() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                                    // node.type=TypeString
                                                    // node.expr.type=TypeString
                                                    // --> nodetype=TypeString
                                                    return ((TypeString) get_channel_name_ASCII().get().add(new TypeString("_osiem")));
                                                }

                                            }

                                        }


                                        /**
                                         * 
                                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                         * node.type=TypeString
                                         * --> nodetype=TypeString
                                         * 
                                         */
                                        public class _param_channel_name_temp
                                            extends Param<TypeString>
                                        {


                                            public java.lang.String id() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                                return "channel_name_temp";
                                            }

                                            protected TypeString _get() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                                // node.type=TypeString
                                                // node.expr.type=TypeString
                                                // --> nodetype=TypeString
                                                return ((TypeString) get_channel_name_ASCII().get().add(new TypeString("_siedem")));
                                            }

                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_name
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "channel_name";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                            // node.type=TypeString
                                            // node.expr.type=TypeString
                                            // --> nodetype=TypeString
                                            return ((TypeString) get_szukajIf().get().access("channel_name_temp"));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_name_ASCII
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "channel_name_ASCII";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                            // node.type=TypeString
                                            // node.expr.type=TypeString
                                            // --> nodetype=TypeString
                                            return ((TypeString) Builtins.trim().call(get_channel_name_ASCIIZ().get()));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_name_ASCIIZ
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "channel_name_ASCIIZ";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                                            // node.type=TypeString
                                            // node._read_type=TypeString
                                            // --> nodetype=TypeString
                                            // format=("|S4")
                                            // format.type=TypeString
                                            // offset=(offset + 4 + index * 4)
                                            // offset.type=unknown
                                            TypeInt offset = ((TypeInt) get_offset().get().add(new TypeInt(4)).add(get_index().get().mul(new TypeInt(4))));
                                            BitForm.String theformat = (new jsignalml.BitForm.String(4));
                                            TypeString input = theformat.read(buffer().source, offset);
                                            return ((TypeString) input);
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                                     * node.type=TypeInt
                                     * --> nodetype=TypeInt
                                     * 
                                     */
                                    public class _param_mapping
                                        extends FunctionParam<TypeInt>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "mapping";
                                        }

                                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:395)
                                            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:651)
                                            return this;
                                        }

                                        public TypeInt call(TypeInt sample) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:678)
                                            // node.type=TypeInt
                                            // node.expr=(data_offset + (sample * number_of_channels + index) * data_cell_size)
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return ((TypeInt) get_data_offset().get().add(sample.mul(get_number_of_channels().get()).add(get_index().get()).mul(get_data_cell_size().get())));
                                        }

                                        public TypeInt call(List<Type> args) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:688)
                                            if (args.size()!= 1) {
                                                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
                                            }
                                            return this.call(((TypeInt) args.get(0)));
                                        }

                                        public long call_p(long sample) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:398)
                                            // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:726)
                                            // node.type=TypeInt
                                            // node.expr=(data_offset + (sample * number_of_channels + index) * data_cell_size)
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return (get_data_offset().get_p()+(((sample*get_number_of_channels().get_p())+ get_index().get_p())*get_data_cell_size().get_p()));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1182)
                                     * 
                                     */
                                    public class index
                                        extends Param<TypeInt>
                                    {

                                        Long get_p = null;

                                        index(TypeInt index) {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1186)
                                            this.cache = index;
                                        }

                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1189)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                            return "index";
                                        }

                                        protected TypeInt _get() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1196)
                                            throw new RuntimeException();
                                        }

                                        public Long get_p() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1200)
                                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                            if (get_p == null) {
                                                get_p = this.get().safeLongValue();
                                            }
                                            return get_p;
                                        }

                                    }

                                }

                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                         * node.type=TypeInt
                         * --> nodetype=unknown
                         * 
                         */
                        public class _param_offset
                            extends Param<Type>
                        {


                            public java.lang.String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "offset";
                            }

                            protected Type _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                                // node.type=TypeInt
                                // node.expr.type=unknown
                                // --> nodetype=unknown
                                return (((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False).isTrue()?get_extended_header_offset().get():get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("offset").add(get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("record_size")).add(new TypeInt(4)));
                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                         * node.type=TypeString
                         * --> nodetype=TypeString
                         * 
                         */
                        public class _param_record_mnemonic
                            extends Param<TypeString>
                        {


                            public java.lang.String id() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "record_mnemonic";
                            }

                            protected TypeString _get() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                                // node.type=TypeString
                                // node._read_type=TypeString
                                // --> nodetype=TypeString
                                // format=("|S2")
                                // format.type=TypeString
                                // offset=(offset)
                                // offset.type=unknown
                                TypeInt offset = ((TypeInt) get_offset().get());
                                BitForm.String theformat = (new jsignalml.BitForm.String(2));
                                TypeString input = theformat.read(buffer().source, offset);
                                return ((TypeString) input);
                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_record_size
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public java.lang.String id() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "record_size";
                            }

                            protected TypeInt _get() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                                // node.type=TypeInt
                                // node._read_type=TypeInt
                                // --> nodetype=TypeInt
                                // format=("<u2")
                                // format.type=TypeString
                                // offset=(offset + 2)
                                // offset.type=unknown
                                TypeInt offset = ((TypeInt) get_offset().get().add(new TypeInt(2)));
                                BitForm.Int.Unsigned16 .LE theformat = (new jsignalml.BitForm.Int.Unsigned16.LE());
                                TypeInt input = theformat.read(buffer().source, offset);
                                return ((TypeInt) input);
                            }

                            public Long get_p() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                                if (get_p == null) {
                                    get_p = this.get().safeLongValue();
                                }
                                return get_p;
                            }

                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1182)
                     * 
                     */
                    public class indexHdr
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        indexHdr(TypeInt index) {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1186)
                            this.cache = index;
                        }

                        public java.lang.String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1189)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "indexHdr";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1196)
                            throw new RuntimeException();
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1200)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_p == null) {
                                get_p = this.get().safeLongValue();
                            }
                            return get_p;
                        }

                    }

                }

            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param__calibration_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "_calibration_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // offset=(25)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(25));
                BitForm.Int.Unsigned8 theformat = (new jsignalml.BitForm.Int.Unsigned8());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_calibration_gain
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "calibration_gain";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=TypeFloat
                // node.expr.type=TypeFloat
                // --> nodetype=TypeFloat
                return ((TypeFloat) new TypeInt(1).div(get__calibration_unit().get()));
            }

            public Double get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_calibration_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "calibration_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("<i2")
                // format.type=TypeString
                // offset=(26)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(26));
                BitForm.Int.Int16 .LE theformat = (new jsignalml.BitForm.Int.Int16.LE());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_cell_size
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data_cell_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(2).pow(get_data_validation_field().get().bin_and(new TypeInt(3)).sub(new TypeInt(1))));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_data_org().get().mul(new TypeInt(16)));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_org
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data_org";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("<u2")
                // format.type=TypeString
                // offset=(28)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(28));
                BitForm.Int.Unsigned16 .LE theformat = (new jsignalml.BitForm.Int.Unsigned16.LE());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_validation_field
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data_validation_field";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|i1")
                // format.type=TypeString
                // offset=(24)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(24));
                BitForm.Int.Int8 theformat = (new jsignalml.BitForm.Int.Int8());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_extended_header_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "extended_header_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_xhdr_org().get().mul(new TypeInt(16)));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_file_type
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "file_type";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeString
                // --> nodetype=TypeString
                // format=("|S1")
                // format.type=TypeString
                // offset=(15)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(15));
                BitForm.String theformat = (new jsignalml.BitForm.String(1));
                TypeString input = theformat.read(buffer().source, offset);
                return ((TypeString) input);
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_magic
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "magic";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=unknown
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) get_signature().get().slice(null, new TypeInt(3), null));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_mapping
            extends FunctionParam<TypeInt>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "mapping";
            }

            public EASYS.File_main._param_mapping get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:395)
                // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:651)
                return this;
            }

            public TypeInt call(TypeInt sample_number, TypeInt channel_number) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:678)
                // node.type=TypeInt
                // node.expr=((number_of_channels * sample_number + channel_number) * data_cell_size + data_offset)
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_number_of_channels().get().mul(sample_number).add(channel_number).mul(get_data_cell_size().get()).add(get_data_offset().get()));
            }

            public TypeInt call(List<Type> args) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:688)
                if (args.size()!= 2) {
                    throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 2);
                }
                return this.call(((TypeInt) args.get(0)), ((TypeInt) args.get(1)));
            }

            public long call_p(long sample_number, long channel_number) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:398)
                // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:726)
                // node.type=TypeInt
                // node.expr=((number_of_channels * sample_number + channel_number) * data_cell_size + data_offset)
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((((get_number_of_channels().get_p()*sample_number)+ channel_number)*get_data_cell_size().get_p())+ get_data_offset().get_p());
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_auxiliary_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "number_of_auxiliary_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // offset=(17)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(17));
                BitForm.Int.Unsigned8 theformat = (new jsignalml.BitForm.Int.Unsigned8());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // offset=(16)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(16));
                BitForm.Int.Unsigned8 theformat = (new jsignalml.BitForm.Int.Unsigned8());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_samples
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "number_of_samples";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("<i4")
                // format.type=TypeString
                // offset=(20)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(20));
                BitForm.Int.Int32 .LE theformat = (new jsignalml.BitForm.Int.Int32.LE());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeMap
         * 
         */
        public class _param_record_name_map
            extends Param<TypeMap>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "record_name_map";
            }

            protected TypeMap _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=unknown
                // node.expr.type=TypeMap
                // --> nodetype=TypeMap
                return ((TypeMap) new TypeMap(new TypeString("AU"), new TypeString("Authentication_key"), new TypeString("FS"), new TypeString("Frequency_of_sampling"), new TypeString("ID"), new TypeString("Patient_ID_number_or_PIC"), new TypeString("RB"), new TypeString("RBLock_structure_definition"), new TypeString("TE"), new TypeString("Text_record"), new TypeString("TI"), new TypeString("Time_info"), new TypeString("TT"), new TypeString("Tag_table"), new TypeString("TX"), new TypeString("Text_record_extension")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeFloat
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "sample_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=TypeFloat
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:638)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_sampling_frequency
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "sampling_frequency";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=TypeFloat
                // node._read_type=TypeInt
                // --> nodetype=TypeFloat
                // format=("<u2")
                // format.type=TypeString
                // offset=(18)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(18));
                BitForm.Int.Unsigned16 .LE theformat = (new jsignalml.BitForm.Int.Unsigned16.LE());
                TypeInt input = theformat.read(buffer().source, offset);
                return TypeFloat.I.make(input);
            }

            public Double get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_signature
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "signature";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=unknown
                // node._read_type=TypeString
                // --> nodetype=TypeString
                // format=("|S15")
                // format.type=TypeString
                // offset=(0)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(0));
                BitForm.String theformat = (new jsignalml.BitForm.String(15));
                TypeString input = theformat.read(buffer().source, offset);
                return ((TypeString) input);
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:409)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_xhdr_org
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:410)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "xhdr_org";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:487)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("<u2")
                // format.type=TypeString
                // offset=(30)
                // offset.type=TypeInt
                TypeInt offset = ((TypeInt) new TypeInt(30));
                BitForm.Int.Unsigned16 .LE theformat = (new jsignalml.BitForm.Int.Unsigned16.LE());
                TypeInt input = theformat.read(buffer().source, offset);
                return ((TypeInt) input);
            }

            public Long get_p() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:255)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:411)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:528)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:670)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }

    }

}
