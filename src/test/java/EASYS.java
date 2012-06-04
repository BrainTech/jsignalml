import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;
import jsignalml.BitForm;
import jsignalml.Builtins;
import jsignalml.Channel;
import jsignalml.ChannelSet;
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
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:175)
 * 
 */
public class EASYS
    extends Signalml
{

    final static Logger log = new Logger(EASYS.class);
    private int channelCounter = 0;
    EASYS.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("EASYS.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            EASYS.File_main obj = get_main();
            register("main", obj);
            obj.createParams();
        }
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("EASYS.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            EASYS.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public java.lang.String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
        return "EASYS";
    }

    public static void main(java.lang.String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tEASYS inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        EASYS reader = new EASYS();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        ChannelSet channelSet = reader.get_set();
        int nrOfChannels = channelSet.getNumberOfChannels();
        int nrOfChannelsToShow = nrOfChannels;
        System.out.println(("Input file for EASYS codec: "+ args[ 0 ]));
        System.out.println((("Input file has "+ nrOfChannels)+" channels"));
        if (argc > 1) {
            nrOfChannelsToShow = argc;
        }
        for (int i = 1; (i<= nrOfChannelsToShow); i ++) {
            int channelNr = (i- 1);
            if (argc > 1) {
                channelNr = Integer.decode(args[i]).intValue();
            }
            Channel channel = channelSet.getChannel(channelNr);
            int nrOfSamples = ((int) channel.getNumberOfSamples());
            int nrOfSamplesToShow = Math.min(nrOfSamples, 10);
            java.lang.String channelName = channel.getChannelName();
            java.lang.String channelType = channel.getChannelType();
            System.out.println(((("Channel #"+ channelNr)+(("["+ channelType)+(" "+ channelName)))+(("] has "+ nrOfSamples)+" samples:")));
            for (int sampleNr = 0; (sampleNr<nrOfSamplesToShow); sampleNr ++) {
                float sampleUnitValue = channel.getSample(sampleNr);
                System.out.println((("\tSample #"+ sampleNr)+(" ---> "+ sampleUnitValue)));
            }
        }
    }

    public File getCurrentFilename() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:412)
        return null;
    }

    public java.lang.String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:186)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:421)
        return null;
    }

    public java.lang.String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:187)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:430)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:188)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:402)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:189)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:439)
    }

    public EASYS.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:932)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
        if (get_main == null) {
            get_main = new EASYS.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:873)
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
     * parent paramClass=_param_unit
     * parent paramClass=_param__calibration_unit
     * parent paramClass=_param_calibration_gain
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
        EASYS.File_main._param_unit get_unit = null;
        EASYS.File_main._param__calibration_unit get__calibration_unit = null;
        EASYS.File_main._param_calibration_gain get_calibration_gain = null;
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
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:914)
            return super.access(name);
        }

        public void register(java.lang.String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:922)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:929)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_main.createParams()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("mapping", get_mapping());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("signature", get_signature());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("magic", get_magic());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("file_type", get_file_type());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("number_of_channels", get_number_of_channels());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sample_unit", get_sample_unit());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("number_of_auxiliary_channels", get_number_of_auxiliary_channels());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sampling_frequency", get_sampling_frequency());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("number_of_samples", get_number_of_samples());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_validation_field", get_data_validation_field());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_cell_size", get_data_cell_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("unit", get_unit());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("_calibration_unit", get__calibration_unit());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("calibration_gain", get_calibration_gain());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("calibration_offset", get_calibration_offset());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_org", get_data_org());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_offset", get_data_offset());
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("xhdr_org", get_xhdr_org());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("extended_header_offset", get_extended_header_offset());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("record_name_map", get_record_name_map());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1359)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                EASYS.File_main.ChannelSet_data obj = get_data();
                register("data", obj);
                obj.createParams();
            }
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:929)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_main.createChannels()");
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1359)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                EASYS.File_main.ChannelSet_data obj = get_data();
                obj.createChannels();
            }
            registerChannelSet(get_data());
        }

        public java.lang.String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:859)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
            return "main";
        }

        public EASYS.File_main._param_mapping get_mapping() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_mapping == null) {
                get_mapping = new EASYS.File_main._param_mapping();
            }
            return get_mapping;
        }

        public EASYS.File_main._param_signature get_signature() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_signature == null) {
                get_signature = new EASYS.File_main._param_signature();
            }
            return get_signature;
        }

        public EASYS.File_main._param_magic get_magic() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_magic == null) {
                get_magic = new EASYS.File_main._param_magic();
            }
            return get_magic;
        }

        public EASYS.File_main._param_file_type get_file_type() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_file_type == null) {
                get_file_type = new EASYS.File_main._param_file_type();
            }
            return get_file_type;
        }

        public EASYS.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_channels == null) {
                get_number_of_channels = new EASYS.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public EASYS.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_unit == null) {
                get_sample_unit = new EASYS.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public EASYS.File_main._param_number_of_auxiliary_channels get_number_of_auxiliary_channels() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_auxiliary_channels == null) {
                get_number_of_auxiliary_channels = new EASYS.File_main._param_number_of_auxiliary_channels();
            }
            return get_number_of_auxiliary_channels;
        }

        public EASYS.File_main._param_sampling_frequency get_sampling_frequency() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sampling_frequency == null) {
                get_sampling_frequency = new EASYS.File_main._param_sampling_frequency();
            }
            return get_sampling_frequency;
        }

        public EASYS.File_main._param_number_of_samples get_number_of_samples() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_samples == null) {
                get_number_of_samples = new EASYS.File_main._param_number_of_samples();
            }
            return get_number_of_samples;
        }

        public EASYS.File_main._param_data_validation_field get_data_validation_field() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_validation_field == null) {
                get_data_validation_field = new EASYS.File_main._param_data_validation_field();
            }
            return get_data_validation_field;
        }

        public EASYS.File_main._param_data_cell_size get_data_cell_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_cell_size == null) {
                get_data_cell_size = new EASYS.File_main._param_data_cell_size();
            }
            return get_data_cell_size;
        }

        public EASYS.File_main._param_unit get_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_unit == null) {
                get_unit = new EASYS.File_main._param_unit();
            }
            return get_unit;
        }

        public EASYS.File_main._param__calibration_unit get__calibration_unit() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get__calibration_unit == null) {
                get__calibration_unit = new EASYS.File_main._param__calibration_unit();
            }
            return get__calibration_unit;
        }

        public EASYS.File_main._param_calibration_gain get_calibration_gain() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_calibration_gain == null) {
                get_calibration_gain = new EASYS.File_main._param_calibration_gain();
            }
            return get_calibration_gain;
        }

        public EASYS.File_main._param_calibration_offset get_calibration_offset() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_calibration_offset == null) {
                get_calibration_offset = new EASYS.File_main._param_calibration_offset();
            }
            return get_calibration_offset;
        }

        public EASYS.File_main._param_data_org get_data_org() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_org == null) {
                get_data_org = new EASYS.File_main._param_data_org();
            }
            return get_data_org;
        }

        public EASYS.File_main._param_data_offset get_data_offset() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_offset == null) {
                get_data_offset = new EASYS.File_main._param_data_offset();
            }
            return get_data_offset;
        }

        public EASYS.File_main._param_xhdr_org get_xhdr_org() {
            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_xhdr_org == null) {
                get_xhdr_org = new EASYS.File_main._param_xhdr_org();
            }
            return get_xhdr_org;
        }

        public EASYS.File_main._param_extended_header_offset get_extended_header_offset() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_extended_header_offset == null) {
                get_extended_header_offset = new EASYS.File_main._param_extended_header_offset();
            }
            return get_extended_header_offset;
        }

        public EASYS.File_main._param_record_name_map get_record_name_map() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_record_name_map == null) {
                get_record_name_map = new EASYS.File_main._param_record_name_map();
            }
            return get_record_name_map;
        }

        public EASYS.File_main.ChannelSet_data get_data() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1356)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data == null) {
                get_data = new EASYS.File_main.ChannelSet_data();
            }
            return get_data;
        }


        /**
         * 
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
         * jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1351)
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1337)
         * 
         */
        public class ChannelSet_data
            extends ChannelSetClass
        {

            EASYS.File_main.ChannelSet_data.Loop_extended_header get_extended_header = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1353)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ChannelSet_data.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    EASYS.File_main.ChannelSet_data.Loop_extended_header obj = get_extended_header();
                    register("extended_header", obj);
                    obj.createParams();
                }
            }

            public void createChannels() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1353)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ChannelSet_data.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    EASYS.File_main.ChannelSet_data.Loop_extended_header obj = get_extended_header();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public java.lang.String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1338)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data";
            }

            public EASYS.File_main.ChannelSet_data.Loop_extended_header get_extended_header() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1063)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_extended_header == null) {
                    get_extended_header = new EASYS.File_main.ChannelSet_data.Loop_extended_header();
                }
                return get_extended_header;
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1064)
            }


            /**
             * 
             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1039)
             * 
             */
            public class Loop_extended_header
                extends OuterLoopClass
            {


                public void createParams() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1060)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("Loop_extended_header.createParams()");
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1060)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("Loop_extended_header.createChannels()");
                }

                public java.lang.String id() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1040)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                    return "extended_header";
                }

                protected TypeList getSequence() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1041)
                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:1076)
                    TypeList range = ((TypeList)(get_xhdr_org().get().isTrue()?Builtins.range().call(new TypeInt(20)):new TypeList()));
                    return range;
                }

                protected EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1304)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1093)
                    return new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner(((TypeInt) index));
                }


                /**
                 * 
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1112)
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1044)
                 * 
                 */
                public class extended_header_inner
                    extends OuterLoopClass.LoopClass
                {

                    final EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr index;
                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr get_if_ext_hdr = null;

                    extended_header_inner(TypeInt indexHdr) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1302)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1322)
                        this.index = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr(indexHdr);
                    }

                    public void createParams() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1114)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("extended_header_inner.createParams()");
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1029)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("indexHdr", this.index);
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr obj = get_if_ext_hdr();
                            register("if_ext_hdr", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1114)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("extended_header_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr obj = get_if_ext_hdr();
                            obj.createChannels();
                        }
                    }

                    public java.lang.String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1045)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "extended_header";
                    }

                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.indexHdr get_indexHdr() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1025)
                        return this.index;
                    }

                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr get_if_ext_hdr() {
                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1154)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_if_ext_hdr == null) {
                            get_if_ext_hdr = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr();
                        }
                        return get_if_ext_hdr;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1149)
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1134)
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
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createParamsIf()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("offset", get_offset());
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("record_mnemonic", get_record_mnemonic());
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("record_size", get_record_size());
                            {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names obj = get_if_record_channel_names();
                                register("if_record_channel_names", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createChannelsIf()");
                            {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names obj = get_if_record_channel_names();
                                obj.createChannels();
                            }
                        }

                        public void createParamsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createParamsElseIf()");
                        }

                        public void createChannelsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createChannelsElseIf()");
                        }

                        public void createParamsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createParamsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1();
                                register("gen_id_1", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if_ext_hdr.createChannelsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 obj = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1();
                                obj.createChannels();
                            }
                        }

                        public boolean hasElseIf() {
                            return false;
                        }

                        public java.lang.String id() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1135)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "if_ext_hdr";
                        }

                        public Type getCondition() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1136)
                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1175)
                            Type test = (((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False).isTrue()?((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False):get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("record_size"));
                            return test;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_offset get_offset() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_offset == null) {
                                get_offset = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_offset();
                            }
                            return get_offset;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_mnemonic get_record_mnemonic() {
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_record_mnemonic == null) {
                                get_record_mnemonic = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_mnemonic();
                            }
                            return get_record_mnemonic;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_size get_record_size() {
                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_record_size == null) {
                                get_record_size = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr._param_record_size();
                            }
                            return get_record_size;
                        }

                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names get_if_record_channel_names() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1154)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_if_record_channel_names == null) {
                                get_if_record_channel_names = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names();
                            }
                            return get_if_record_channel_names;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                         * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1206)
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1189)
                         * parent paramClass=_param_record_size
                         * 
                         */
                        public class Else_gen_id_1
                            extends ConditionalClass.ElseBranchClass
                        {

                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size get_record_size = null;

                            public void createParams() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Else_gen_id_1.createParams()");
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                register("record_size", get_record_size());
                            }

                            public void createChannels() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Else_gen_id_1.createChannels()");
                            }

                            public java.lang.String id() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1190)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "gen_id_1";
                            }

                            public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size get_record_size() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                if (get_record_size == null) {
                                    get_record_size = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.Else_gen_id_1 ._param_record_size();
                                }
                                return get_record_size;
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                             * node.type=TypeInt
                             * --> nodetype=TypeInt
                             * 
                             */
                            public class _param_record_size
                                extends Param<TypeInt>
                            {

                                Long get_p = null;

                                public java.lang.String id() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                    return "record_size";
                                }

                                protected TypeInt _get() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                                    // node.type=TypeInt
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return ((TypeInt) new TypeInt(0));
                                }

                                public Long get_p() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                                    // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                    if (get_p == null) {
                                        get_p = this.get().safeLongValue();
                                    }
                                    return get_p;
                                }

                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1149)
                         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1134)
                         * 
                         */
                        public class If_if_record_channel_names
                            extends ConditionalClass
                        {

                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels get_channels = null;

                            public void createParamsIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createParamsIf()");
                                {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels obj = get_channels();
                                    register("channels", obj);
                                    obj.createParams();
                                }
                            }

                            public void createChannelsIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createChannelsIf()");
                                {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels obj = get_channels();
                                    obj.createChannels();
                                    obj.createLoopChannels();
                                }
                            }

                            public void createParamsElseIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createParamsElseIf()");
                            }

                            public void createChannelsElseIf() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createChannelsElseIf()");
                            }

                            public void createParamsElse() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createParamsElse()");
                            }

                            public void createChannelsElse() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("If_if_record_channel_names.createChannelsElse()");
                            }

                            public boolean hasElseIf() {
                                return false;
                            }

                            public java.lang.String id() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1135)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "if_record_channel_names";
                            }

                            public Type getCondition() {
                                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1136)
                                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1175)
                                Type test = ((get_record_mnemonic().get().compareTo(new TypeString("CN")) == 0)?TypeInt.True:TypeInt.False);
                                return test;
                            }

                            public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels get_channels() {
                                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1063)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                if (get_channels == null) {
                                    get_channels = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels();
                                }
                                return get_channels;
                                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1064)
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1039)
                             * 
                             */
                            public class Loop_channels
                                extends OuterLoopClass
                            {


                                public void createParams() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1060)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("Loop_channels.createParams()");
                                }

                                public void createChannels() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1060)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("Loop_channels.createChannels()");
                                }

                                public java.lang.String id() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1040)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                    return "channels";
                                }

                                protected TypeList getSequence() {
                                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1041)
                                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:1076)
                                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                                    return range;
                                }

                                protected EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner createLoop(Type index) {
                                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1304)
                                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1093)
                                    return new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner(((TypeInt) index));
                                }


                                /**
                                 * 
                                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1112)
                                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1044)
                                 * parent paramClass=_param_channel_name_ASCIIZ
                                 * parent paramClass=_param_channel_name
                                 * parent paramClass=_param_channel_type
                                 * parent paramClass=_param_mapping
                                 * 
                                 */
                                public class channels_inner
                                    extends OuterLoopClass.LoopClass
                                {

                                    final EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index index;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0 = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ get_channel_name_ASCIIZ = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_type get_channel_type = null;
                                    EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get_mapping = null;

                                    channels_inner(TypeInt index) {
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1302)
                                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1322)
                                        this.index = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index(index);
                                    }

                                    public void createParams() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1114)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("channels_inner.createParams()");
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1029)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("index", this.index);
                                        {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1408)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                                            register("gen_id_0", obj);
                                            obj.createParams();
                                        }
                                        // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("channel_name_ASCIIZ", get_channel_name_ASCIIZ());
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("channel_name", get_channel_name());
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("channel_type", get_channel_type());
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("mapping", get_mapping());
                                    }

                                    public void createChannels() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1043)
                                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1114)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("channels_inner.createChannels()");
                                        {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1408)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                                            obj.createChannels();
                                        }
                                        registerChannel(get_gen_id_0());
                                    }

                                    public java.lang.String id() {
                                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1045)
                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                        return "channels";
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.index get_index() {
                                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1025)
                                        return this.index;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0() {
                                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1405)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                        if (get_gen_id_0 == null) {
                                            get_gen_id_0 = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner.Channel_gen_id_0();
                                        }
                                        return get_gen_id_0;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ get_channel_name_ASCIIZ() {
                                        // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                        if (get_channel_name_ASCIIZ == null) {
                                            get_channel_name_ASCIIZ = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name_ASCIIZ();
                                        }
                                        return get_channel_name_ASCIIZ;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                        if (get_channel_name == null) {
                                            get_channel_name = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_name();
                                        }
                                        return get_channel_name;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_type get_channel_type() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                        if (get_channel_type == null) {
                                            get_channel_type = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_channel_type();
                                        }
                                        return get_channel_type;
                                    }

                                    public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get_mapping() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                        if (get_mapping == null) {
                                            get_mapping = new EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping();
                                        }
                                        return get_mapping;
                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1400)
                                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1371)
                                     * 
                                     */
                                    public class Channel_gen_id_0
                                        extends ChannelClass
                                    {

                                        private int channelNum = channelCounter ++;

                                        public void createParams() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1402)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("Channel_gen_id_0.createParams()");
                                        }

                                        public void createChannels() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1402)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("Channel_gen_id_0.createChannels()");
                                        }

                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1373)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "gen_id_0";
                                        }

                                        protected MyBuffer _buffer() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1374)
                                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1420)
                                            return buffer();
                                        }

                                        public TypeString getSampleFormat() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1375)
                                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1432)
                                            // node.format.type=TypeString
                                            TypeString value = new TypeString("<i2");
                                            return ((TypeString) value);
                                        }

                                        public TypeInt mapSample(long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1376)
                                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1445)
                                            Type value = get_mapping().get();
                                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                                        }

                                        public float getSample(long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1377)
                                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1460)
                                            TypeString format_ = this.getSampleFormat();
                                            BitForm format = BitForm.get(format_);
                                            ByteBuffer buffer = _buffer().source;
                                            if (isBinary()) {
                                                float rawValue = format.read(buffer, ((int) get_mapping().call_p(sample)));
                                                return applyLinearTransformation(rawValue);
                                            } else {
                                                float rawValue = getScanner().readFloat(((int) get_mapping().call_p(sample)));
                                                return applyLinearTransformation(rawValue);
                                            }
                                        }

                                        public void getSamples(FloatBuffer dst, long sample) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1378)
                                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1537)
                                            TypeString format_ = this.getSampleFormat();
                                            BitForm format = BitForm.get(format_);
                                            ByteBuffer buffer = _buffer().source;
                                            int count = dst.remaining();
                                            if (isBinary()) {
                                                while (count--> 0) {
                                                    float rawValue = format.read(buffer, ((int) get_mapping().call_p(sample ++)));
                                                    dst.put(applyLinearTransformation(rawValue));
                                                }
                                            } else {
                                                while (count--> 0) {
                                                    float rawValue = getScanner().readFloat(((int) get_mapping().call_p(sample ++)));
                                                    dst.put(applyLinearTransformation(rawValue));
                                                }
                                            }
                                        }

                                        private float applyLinearTransformation(float rawValue) {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1379)
                                            // jsignalml.JavaClassGen.applyLinearTransformationMethod(JavaClassGen.java:1621)
                                            float calibGain = getCalibrationGain().getValue().floatValue();
                                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                                            float sampleUnit = getSampleUnit().getValue().floatValue();
                                            return (((rawValue-calibOffs)*calibGain)*sampleUnit);
                                        }

                                        public double getSamplingFrequency() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1380)
                                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1654)
                                            Type value = get_sampling_frequency().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast.getValue();
                                        }

                                        public long getNumberOfSamples() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1381)
                                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1667)
                                            Type value = get_number_of_samples().get();
                                            TypeInt cast = TypeInt.I.make(value);
                                            return cast.safeLongValue();
                                        }

                                        public java.lang.String getChannelName() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1382)
                                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1693)
                                            Type value = get_channel_name().get();
                                            TypeString stringValue = ((TypeString) value);
                                            java.lang.String strValue = stringValue.getValue();
                                            if (strValue.equals("")) {
                                                return ("L"+ channelNum);
                                            }
                                            return strValue;
                                        }

                                        public java.lang.String getChannelType() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1383)
                                            // jsignalml.JavaClassGen.getChannelTypeMethod(JavaClassGen.java:1729)
                                            Type value = get_channel_type().get();
                                            TypeString stringValue = ((TypeString) value);
                                            return stringValue.getValue();
                                        }

                                        public TypeFloat getCalibrationGain() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1384)
                                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1745)
                                            Type value = get_calibration_gain().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                        public TypeFloat getCalibrationOffset() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1385)
                                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1761)
                                            Type value = get_calibration_offset().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                        public TypeFloat getSampleUnit() {
                                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1386)
                                            // jsignalml.JavaClassGen.getSampleUnitMethod(JavaClassGen.java:1681)
                                            Type value = get_sample_unit().get();
                                            TypeFloat cast = TypeFloat.I.make(value);
                                            return cast;
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_name
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "channel_name";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                                            // node.type=TypeString
                                            // node.expr.type=TypeString
                                            // --> nodetype=TypeString
                                            return ((TypeString) Builtins.trim().call(get_channel_name_ASCIIZ().get()));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_name_ASCIIZ
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "channel_name_ASCIIZ";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                                     * node.type=TypeString
                                     * --> nodetype=TypeString
                                     * 
                                     */
                                    public class _param_channel_type
                                        extends Param<TypeString>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "channel_type";
                                        }

                                        protected TypeString _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                                            // node.type=TypeString
                                            // node.expr.type=TypeString
                                            // --> nodetype=TypeString
                                            return ((TypeString) new TypeString("EEG"));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                                     * node.type=TypeInt
                                     * --> nodetype=TypeInt
                                     * 
                                     */
                                    public class _param_mapping
                                        extends FunctionParam<TypeInt>
                                    {


                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "mapping";
                                        }

                                        public EASYS.File_main.ChannelSet_data.Loop_extended_header.extended_header_inner.If_if_ext_hdr.If_if_record_channel_names.Loop_channels.channels_inner._param_mapping get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:454)
                                            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:751)
                                            return this;
                                        }

                                        public TypeInt call(TypeInt sample) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:455)
                                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:784)
                                            // node.type=TypeInt
                                            // node.expr=(data_offset + (sample * number_of_channels + index) * data_cell_size)
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return ((TypeInt) get_data_offset().get().add(sample.mul(get_number_of_channels().get()).add(get_index().get()).mul(get_data_cell_size().get())));
                                        }

                                        public TypeInt call(List<Type> args) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:455)
                                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:794)
                                            if (args.size()!= 1) {
                                                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
                                            }
                                            return this.call(((TypeInt) args.get(0)));
                                        }

                                        public long call_p(long sample) {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                                            // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:832)
                                            // node.type=TypeInt
                                            // node.expr=(data_offset + (sample * number_of_channels + index) * data_cell_size)
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return (get_data_offset().get_p()+(((sample*get_number_of_channels().get_p())+ get_index().get_p())*get_data_cell_size().get_p()));
                                        }

                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1293)
                                     * 
                                     */
                                    public class index
                                        extends Param<TypeInt>
                                    {

                                        Long get_p = null;

                                        index(TypeInt index) {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1297)
                                            this.cache = index;
                                        }

                                        public java.lang.String id() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1300)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                            return "index";
                                        }

                                        protected TypeInt _get() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1307)
                                            throw new RuntimeException();
                                        }

                                        public Long get_p() {
                                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1311)
                                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
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
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeInt
                         * --> nodetype=unknown
                         * 
                         */
                        public class _param_offset
                            extends Param<Type>
                        {


                            public java.lang.String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "offset";
                            }

                            protected Type _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                                // node.type=TypeInt
                                // node.expr.type=unknown
                                // --> nodetype=unknown
                                return (((get_indexHdr().get().compareTo(new TypeInt(0)) == 0)?TypeInt.True:TypeInt.False).isTrue()?get_extended_header_offset().get():get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("offset").add(get_extended_header().get().index(get_indexHdr().get().sub(new TypeInt(1))).access("if_ext_hdr").access("record_size")).add(new TypeInt(4)));
                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeString
                         * --> nodetype=TypeString
                         * 
                         */
                        public class _param_record_mnemonic
                            extends Param<TypeString>
                        {


                            public java.lang.String id() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "record_mnemonic";
                            }

                            protected TypeString _get() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_record_size
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public java.lang.String id() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "record_size";
                            }

                            protected TypeInt _get() {
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                if (get_p == null) {
                                    get_p = this.get().safeLongValue();
                                }
                                return get_p;
                            }

                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1293)
                     * 
                     */
                    public class indexHdr
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        indexHdr(TypeInt index) {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1297)
                            this.cache = index;
                        }

                        public java.lang.String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1300)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "indexHdr";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1307)
                            throw new RuntimeException();
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1311)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
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
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param__calibration_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "_calibration_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_calibration_gain
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "calibration_gain";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeFloat
                // node.expr.type=TypeFloat
                // --> nodetype=TypeFloat
                return ((TypeFloat) new TypeInt(1).div(get__calibration_unit().get()));
            }

            public Double get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_calibration_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "calibration_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_cell_size
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_cell_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(2).pow(get_data_validation_field().get().bin_and(new TypeInt(3)).sub(new TypeInt(1))));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_data_org().get().mul(new TypeInt(16)));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_org
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_org";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_data_validation_field
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_validation_field";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_extended_header_offset
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "extended_header_offset";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=unknown
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_xhdr_org().get().mul(new TypeInt(16)));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_file_type
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "file_type";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_magic
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "magic";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=unknown
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) get_signature().get().slice(null, new TypeInt(3), null));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_mapping
            extends FunctionParam<TypeInt>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "mapping";
            }

            public EASYS.File_main._param_mapping get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:454)
                // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:751)
                return this;
            }

            public TypeInt call(TypeInt sample_number, TypeInt channel_number) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:455)
                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:784)
                // node.type=TypeInt
                // node.expr=((number_of_channels * sample_number + channel_number) * data_cell_size + data_offset)
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_number_of_channels().get().mul(sample_number).add(channel_number).mul(get_data_cell_size().get()).add(get_data_offset().get()));
            }

            public TypeInt call(List<Type> args) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:455)
                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:794)
                if (args.size()!= 2) {
                    throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 2);
                }
                return this.call(((TypeInt) args.get(0)), ((TypeInt) args.get(1)));
            }

            public long call_p(long sample_number, long channel_number) {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:832)
                // node.type=TypeInt
                // node.expr=((number_of_channels * sample_number + channel_number) * data_cell_size + data_offset)
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((((get_number_of_channels().get_p()*sample_number)+ channel_number)*get_data_cell_size().get_p())+ get_data_offset().get_p());
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_auxiliary_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_auxiliary_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_samples
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_samples";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeMap
         * 
         */
        public class _param_record_name_map
            extends Param<TypeMap>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "record_name_map";
            }

            protected TypeMap _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=unknown
                // node.expr.type=TypeMap
                // --> nodetype=TypeMap
                return ((TypeMap) new TypeMap(new TypeString("AU"), new TypeString("Authentication_key"), new TypeString("FS"), new TypeString("Frequency_of_sampling"), new TypeString("ID"), new TypeString("Patient_ID_number_or_PIC"), new TypeString("RB"), new TypeString("RBLock_structure_definition"), new TypeString("TE"), new TypeString("Text_record"), new TypeString("TI"), new TypeString("Time_info"), new TypeString("TT"), new TypeString("Tag_table"), new TypeString("TX"), new TypeString("Text_record_extension")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeFloat
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "sample_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeFloat
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:738)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_sampling_frequency
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "sampling_frequency";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_signature
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "signature";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_unit
            extends Param<TypeString>
        {


            public java.lang.String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "unit";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("\u03bcV"));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:468)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_xhdr_org
            extends Param<TypeInt>
        {

            Long get_p = null;

            public java.lang.String id() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:469)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "xhdr_org";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:558)
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
                // jsignalml.ASTNode$BinaryParam._accept(ASTNode.java:279)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:470)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:599)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:776)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }

    }

}
