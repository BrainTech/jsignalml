import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;
import jsignalml.BitForm;
import jsignalml.Builtins;
import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.MyBuffer;
import jsignalml.TextBuffer;
import jsignalml.Type;
import jsignalml.TypeFloat;
import jsignalml.TypeInt;
import jsignalml.TypeList;
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
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:173)
 * 
 */
public class M4D
    extends Signalml
{

    final static Logger log = new Logger(M4D.class);
    M4D.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:178)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
        log.debug("M4D.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:896)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            M4D.File_main obj = get_main();
            register("main", obj);
            obj.createParams();
        }
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:178)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
        log.debug("M4D.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:896)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            M4D.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:181)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
        return "M4D";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:182)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:306)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tM4D inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        M4D reader = new M4D();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        ChannelSet channelSet = reader.get_set();
        int nrOfChannels = channelSet.getNumberOfChannels();
        int nrOfChannelsToShow = nrOfChannels;
        System.out.println(("Input file for M4D codec: "+ args[ 0 ]));
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
            String channelName = channel.getChannelName();
            String channelType = channel.getChannelTypeName();
            System.out.println(((("Channel #"+ channelNr)+(("["+ channelType)+(" "+ channelName)))+(("] has "+ nrOfSamples)+" samples:")));
            for (int sampleNr = 0; (sampleNr<nrOfSamplesToShow); sampleNr ++) {
                float sampleUnitValue = channel.getSample(sampleNr);
                System.out.println((("\tSample #"+ sampleNr)+(" ---> "+ sampleUnitValue)));
            }
        }
    }

    public File getCurrentFilename() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:420)
        return null;
    }

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:429)
        return null;
    }

    public String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:438)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:186)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:410)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:122)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:187)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:447)
    }

    public M4D.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:893)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
        if (get_main == null) {
            get_main = new M4D.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:834)
     * parent paramClass=_param_channel_name
     * parent paramClass=_param_channel_type_name
     * parent paramClass=_param_number_of_channels
     * parent paramClass=_param_sampling_frequency
     * parent paramClass=_param_number_of_samples
     * parent paramClass=_param_meg_chan_index
     * parent paramClass=_param_total_epochs
     * parent paramClass=_param_slices_per_epoch
     * parent paramClass=_param_total_channels
     * parent paramClass=_param_data_filename
     * parent paramClass=_param_sample_unit
     * parent paramClass=_param_calibration_gain
     * parent paramClass=_param_msi_format
     * parent paramClass=_param_sample_size
     * parent paramClass=_param_sample_format
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        M4D.File_main._param_channel_name get_channel_name = null;
        M4D.File_main._param_channel_type_name get_channel_type_name = null;
        M4D.File_main._param_number_of_channels get_number_of_channels = null;
        M4D.File_main._param_sampling_frequency get_sampling_frequency = null;
        M4D.File_main._param_number_of_samples get_number_of_samples = null;
        M4D.File_main._param_meg_chan_index get_meg_chan_index = null;
        M4D.File_main._param_total_epochs get_total_epochs = null;
        M4D.File_main._param_slices_per_epoch get_slices_per_epoch = null;
        M4D.File_main._param_total_channels get_total_channels = null;
        M4D.File_main._param_data_filename get_data_filename = null;
        M4D.File_main._param_sample_unit get_sample_unit = null;
        M4D.File_main._param_calibration_gain get_calibration_gain = null;
        M4D.File_main.File_data_file get_data_file = null;
        M4D.File_main._param_msi_format get_msi_format = null;
        M4D.File_main.If_size get_size = null;
        M4D.File_main.If_format get_format = null;
        M4D.File_main._param_sample_size get_sample_size = null;
        M4D.File_main._param_sample_format get_sample_format = null;
        M4D.File_main.ChannelSet_data_test get_data_test = null;

        public File_main() {
            isBinary = false;
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:875)
            return super.access(name);
        }

        public void register(String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:883)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:890)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            log.debug("File_main.createParams()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("channel_name", get_channel_name());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("channel_type_name", get_channel_type_name());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("number_of_channels", get_number_of_channels());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("sampling_frequency", get_sampling_frequency());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("number_of_samples", get_number_of_samples());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("meg_chan_index", get_meg_chan_index());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("total_epochs", get_total_epochs());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("slices_per_epoch", get_slices_per_epoch());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("total_channels", get_total_channels());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("data_filename", get_data_filename());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("sample_unit", get_sample_unit());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("calibration_gain", get_calibration_gain());
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:896)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.File_data_file obj = get_data_file();
                register("data_file", obj);
                obj.createParams();
            }
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("msi_format", get_msi_format());
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.If_size obj = get_size();
                register("size", obj);
                obj.createParams();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.If_format obj = get_format();
                register("format", obj);
                obj.createParams();
            }
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("sample_size", get_sample_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            register("sample_format", get_sample_format());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1320)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.ChannelSet_data_test obj = get_data_test();
                register("data_test", obj);
                obj.createParams();
            }
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:890)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
            log.debug("File_main.createChannels()");
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:896)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.File_data_file obj = get_data_file();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.If_size obj = get_size();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.If_format obj = get_format();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1320)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                M4D.File_main.ChannelSet_data_test obj = get_data_test();
                obj.createChannels();
            }
            registerChannelSet(get_data_test());
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:820)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
            return "main";
        }

        public M4D.File_main._param_channel_name get_channel_name() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_channel_name == null) {
                get_channel_name = new M4D.File_main._param_channel_name();
            }
            return get_channel_name;
        }

        public M4D.File_main._param_channel_type_name get_channel_type_name() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_channel_type_name == null) {
                get_channel_type_name = new M4D.File_main._param_channel_type_name();
            }
            return get_channel_type_name;
        }

        public M4D.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_number_of_channels == null) {
                get_number_of_channels = new M4D.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public M4D.File_main._param_sampling_frequency get_sampling_frequency() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_sampling_frequency == null) {
                get_sampling_frequency = new M4D.File_main._param_sampling_frequency();
            }
            return get_sampling_frequency;
        }

        public M4D.File_main._param_number_of_samples get_number_of_samples() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_number_of_samples == null) {
                get_number_of_samples = new M4D.File_main._param_number_of_samples();
            }
            return get_number_of_samples;
        }

        public M4D.File_main._param_meg_chan_index get_meg_chan_index() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_meg_chan_index == null) {
                get_meg_chan_index = new M4D.File_main._param_meg_chan_index();
            }
            return get_meg_chan_index;
        }

        public M4D.File_main._param_total_epochs get_total_epochs() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_total_epochs == null) {
                get_total_epochs = new M4D.File_main._param_total_epochs();
            }
            return get_total_epochs;
        }

        public M4D.File_main._param_slices_per_epoch get_slices_per_epoch() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_slices_per_epoch == null) {
                get_slices_per_epoch = new M4D.File_main._param_slices_per_epoch();
            }
            return get_slices_per_epoch;
        }

        public M4D.File_main._param_total_channels get_total_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_total_channels == null) {
                get_total_channels = new M4D.File_main._param_total_channels();
            }
            return get_total_channels;
        }

        public M4D.File_main._param_data_filename get_data_filename() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_data_filename == null) {
                get_data_filename = new M4D.File_main._param_data_filename();
            }
            return get_data_filename;
        }

        public M4D.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_sample_unit == null) {
                get_sample_unit = new M4D.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public M4D.File_main._param_calibration_gain get_calibration_gain() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_calibration_gain == null) {
                get_calibration_gain = new M4D.File_main._param_calibration_gain();
            }
            return get_calibration_gain;
        }

        public M4D.File_main.File_data_file get_data_file() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:893)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_data_file == null) {
                get_data_file = new M4D.File_main.File_data_file();
            }
            return get_data_file;
        }

        public M4D.File_main._param_msi_format get_msi_format() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_msi_format == null) {
                get_msi_format = new M4D.File_main._param_msi_format();
            }
            return get_msi_format;
        }

        public M4D.File_main.If_size get_size() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1115)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_size == null) {
                get_size = new M4D.File_main.If_size();
            }
            return get_size;
        }

        public M4D.File_main.If_format get_format() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1115)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_format == null) {
                get_format = new M4D.File_main.If_format();
            }
            return get_format;
        }

        public M4D.File_main._param_sample_size get_sample_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_sample_size == null) {
                get_sample_size = new M4D.File_main._param_sample_size();
            }
            return get_sample_size;
        }

        public M4D.File_main._param_sample_format get_sample_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_sample_format == null) {
                get_sample_format = new M4D.File_main._param_sample_format();
            }
            return get_sample_format;
        }

        public M4D.File_main.ChannelSet_data_test get_data_test() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1317)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
            if (get_data_test == null) {
                get_data_test = new M4D.File_main.ChannelSet_data_test();
            }
            return get_data_test;
        }


        /**
         * 
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
         * jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1312)
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1298)
         * 
         */
        public class ChannelSet_data_test
            extends ChannelSetClass
        {

            M4D.File_main.ChannelSet_data_test.Loop_channels get_channels = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1314)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("ChannelSet_data_test.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    register("channels", obj);
                    obj.createParams();
                }
            }

            public void createChannels() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1297)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1314)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("ChannelSet_data_test.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:141)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1299)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "data_test";
            }

            public M4D.File_main.ChannelSet_data_test.Loop_channels get_channels() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1024)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_channels == null) {
                    get_channels = new M4D.File_main.ChannelSet_data_test.Loop_channels();
                }
                return get_channels;
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1025)
            }


            /**
             * 
             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1000)
             * 
             */
            public class Loop_channels
                extends OuterLoopClass
            {


                public void createParams() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1021)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("Loop_channels.createParams()");
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:999)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1021)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("Loop_channels.createChannels()");
                }

                public String id() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1001)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                    return "channels";
                }

                protected TypeList getSequence() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1002)
                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:1037)
                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                    return range;
                }

                protected M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1265)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1054)
                    return new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner(((TypeInt) index));
                }


                /**
                 * 
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1004)
                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1073)
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1005)
                 * parent paramClass=_param_channel_name
                 * parent paramClass=_param_channel_type_name
                 * parent paramClass=_param_single_channel_name_pattern
                 * parent paramClass=_param_channel_index
                 * parent paramClass=_param_single_channel_index_pattern
                 * parent paramClass=_param_mapping
                 * 
                 */
                public class channels_inner
                    extends OuterLoopClass.LoopClass
                {

                    final M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index index;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_4 get_gen_id_4 = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_type_name get_channel_type_name = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern get_single_channel_name_pattern = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index get_channel_index = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern get_single_channel_index_pattern = null;
                    M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get_mapping = null;

                    channels_inner(TypeInt index) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1263)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1283)
                        this.index = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index(index);
                    }

                    public void createParams() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1004)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1075)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("channels_inner.createParams()");
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:979)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:990)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("index", this.index);
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1369)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                            M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_4 obj = get_gen_id_4();
                            register("gen_id_4", obj);
                            obj.createParams();
                        }
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("channel_name", get_channel_name());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("channel_type_name", get_channel_type_name());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("single_channel_name_pattern", get_single_channel_name_pattern());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("channel_index", get_channel_index());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("single_channel_index_pattern", get_single_channel_index_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("mapping", get_mapping());
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1004)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1075)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("channels_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1369)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                            M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_4 obj = get_gen_id_4();
                            obj.createChannels();
                        }
                        registerChannel(get_gen_id_4());
                    }

                    public String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:526)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1006)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                        return "channels";
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index get_index() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:979)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:986)
                        return this.index;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_4 get_gen_id_4() {
                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1366)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_gen_id_4 == null) {
                            get_gen_id_4 = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_4();
                        }
                        return get_gen_id_4;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_channel_name == null) {
                            get_channel_name = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name();
                        }
                        return get_channel_name;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_type_name get_channel_type_name() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_channel_type_name == null) {
                            get_channel_type_name = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_type_name();
                        }
                        return get_channel_type_name;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern get_single_channel_name_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_single_channel_name_pattern == null) {
                            get_single_channel_name_pattern = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern();
                        }
                        return get_single_channel_name_pattern;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index get_channel_index() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_channel_index == null) {
                            get_channel_index = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index();
                        }
                        return get_channel_index;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern get_single_channel_index_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_single_channel_index_pattern == null) {
                            get_single_channel_index_pattern = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern();
                        }
                        return get_single_channel_index_pattern;
                    }

                    public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get_mapping() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_mapping == null) {
                            get_mapping = new M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping();
                        }
                        return get_mapping;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1361)
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1332)
                     * 
                     */
                    public class Channel_gen_id_4
                        extends ChannelClass
                    {


                        public void createParams() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1363)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                            log.debug("Channel_gen_id_4.createParams()");
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1331)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1363)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                            log.debug("Channel_gen_id_4.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1334)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "gen_id_4";
                        }

                        protected MyBuffer _buffer() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1335)
                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1378)
                            return buffer();
                        }

                        public TypeString getSampleFormat() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1390)
                            // node.format.type=TypeString
                            TypeString value = get_sample_format().get();
                            return ((TypeString) value);
                        }

                        public TypeInt mapSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1337)
                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1403)
                            Type value = get_mapping().get();
                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                        }

                        public float getSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1338)
                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1419)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_data_file().get();
                            Signalml.FileClass fileHandler = ((Signalml.FileClass) dataFileId);
                            File file = fileHandler.getCurrentFilename();
                            if (file == null) {
                                fileHandler.open(null);
                            }
                            ByteBuffer buffer = fileHandler.buffer().source;
                            if (fileHandler.isBinary()) {
                                float rawValue = format.read(buffer, ((int) get_mapping().call_p(sample)));
                                return applyLinearTransformation(rawValue);
                            } else {
                                float rawValue = fileHandler.getScanner().readFloat(((int) get_mapping().call_p(sample)));
                                return applyLinearTransformation(rawValue);
                            }
                        }

                        public void getSamples(FloatBuffer dst, long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1339)
                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1496)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_data_file().get();
                            Signalml.FileClass fileHandler = ((Signalml.FileClass) dataFileId);
                            File file = fileHandler.getCurrentFilename();
                            if (file == null) {
                                fileHandler.open(null);
                            }
                            ByteBuffer buffer = fileHandler.buffer().source;
                            int count = dst.remaining();
                            if (fileHandler.isBinary()) {
                                while (count--> 0) {
                                    float rawValue = format.read(buffer, ((int) get_mapping().call_p(sample ++)));
                                    dst.put(applyLinearTransformation(rawValue));
                                }
                            } else {
                                while (count--> 0) {
                                    float rawValue = fileHandler.getScanner().readFloat(((int) get_mapping().call_p(sample ++)));
                                    dst.put(applyLinearTransformation(rawValue));
                                }
                            }
                        }

                        private float applyLinearTransformation(float rawValue) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1340)
                            // jsignalml.JavaClassGen.applyLinearTransformationMethod(JavaClassGen.java:1580)
                            float calibGain = getCalibrationGain().getValue().floatValue();
                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                            float sampleUnit = getSampleUnit().getValue().floatValue();
                            return (((rawValue-calibOffs)*calibGain)*sampleUnit);
                        }

                        public double getSamplingFrequency() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1341)
                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1613)
                            Type value = get_sampling_frequency().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast.getValue();
                        }

                        public long getNumberOfSamples() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1342)
                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1626)
                            Type value = get_number_of_samples().get();
                            TypeInt cast = TypeInt.I.make(value);
                            return cast.safeLongValue();
                        }

                        public String getChannelName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1343)
                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1652)
                            Type value = get_channel_name().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public String getChannelTypeName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1344)
                            // jsignalml.JavaClassGen.getChannelTypeNameMethod(JavaClassGen.java:1671)
                            Type value = get_channel_type_name().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public TypeFloat getCalibrationGain() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1345)
                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1691)
                            Type value = get_calibration_gain().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getCalibrationOffset() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1346)
                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1711)
                            TypeFloat cast = new TypeFloat("0");
                            return cast;
                        }

                        public TypeFloat getSampleUnit() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:189)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1347)
                            // jsignalml.JavaClassGen.getSampleUnitMethod(JavaClassGen.java:1640)
                            Type value = get_sample_unit().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_channel_index
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "channel_index";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                            // node.type=TypeInt
                            // node._read_type=TypeInt
                            // --> nodetype=TypeInt
                            // format=("|u1")
                            // format.type=TypeString
                            // line=(-1)
                            // line.type=TypeInt
                            // pattern=(single_channel_index_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_single_channel_index_pattern().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
                            TextBuffer textBuf = textBuffer();
                            TypeInt _t = null;
                            TypeInt value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                            if (get_p == null) {
                                get_p = this.get().safeLongValue();
                            }
                            return get_p;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_name
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "channel_name";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                            // node.type=TypeString
                            // node._read_type=TypeString
                            // --> nodetype=TypeString
                            // format=("|S1")
                            // format.type=TypeString
                            // line=(-1)
                            // line.type=TypeInt
                            // pattern=(single_channel_name_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_single_channel_name_pattern().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
                            TextBuffer textBuf = textBuffer();
                            TypeString _t = null;
                            TypeString value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_type_name
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "channel_type_name";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("MEG"));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_mapping
                        extends FunctionParam<TypeInt>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "mapping";
                        }

                        public M4D.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:462)
                            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:718)
                            return this;
                        }

                        public TypeInt call(TypeInt sample) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:463)
                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:745)
                            // node.type=TypeInt
                            // node.expr=((sample * total_channels + channel_index - 1) * sample_size)
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) sample.mul(get_total_channels().get()).add(get_channel_index().get()).sub(new TypeInt(1)).mul(get_sample_size().get()));
                        }

                        public TypeInt call(List<Type> args) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:463)
                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:755)
                            if (args.size()!= 1) {
                                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
                            }
                            return this.call(((TypeInt) args.get(0)));
                        }

                        public long call_p(long sample) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:465)
                            // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:793)
                            // node.type=TypeInt
                            // node.expr=((sample * total_channels + channel_index - 1) * sample_size)
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((((sample*get_total_channels().get_p())+ get_channel_index().get_p())- 1)*get_sample_size().get_p());
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_single_channel_index_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "single_channel_index_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("MSI.MegChanIndex: ([0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_single_channel_name_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "single_channel_name_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("MSI.MegChanNames: ([a-zA-Z0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([A-Za-z0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1254)
                     * 
                     */
                    public class index
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        index(TypeInt index) {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1258)
                            this.cache = index;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1261)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "index";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1268)
                            throw new RuntimeException();
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:498)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:978)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1272)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
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
         * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
         * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:834)
         * 
         */
        public class File_data_file
            extends Signalml.FileClass
        {


            public File_data_file() {
                File main = default_filename;
                Integer endIndex = (main.getAbsolutePath().length()-main.getName().length());
                String dirname = main.getAbsolutePath().substring(0, endIndex);
                String filename = (dirname + get_data_filename().get().value);
                currentFilename = new File(filename);
            }

            public Type access(String name) {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:875)
                return super.access(name);
            }

            public void register(String name, Context child) {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:883)
                super.register(name, child);
            }

            public void createParams() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:890)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("File_data_file.createParams()");
            }

            public void createChannels() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:819)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:890)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("File_data_file.createChannels()");
            }

            public String id() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:435)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:820)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "data_file";
            }

        }


        /**
         * 
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1110)
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1095)
         * parent paramClass=_param_sample_format_1
         * 
         */
        public class If_format
            extends ConditionalClass
        {

            M4D.File_main.If_format._param_sample_format_1 get_sample_format_1 = null;
            M4D.File_main.If_format.ElseIf_gen_id_2 get_gen_id_2 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                register("sample_format_1", get_sample_format_1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1222)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.If_format.ElseIf_gen_id_2 obj = get_gen_id_2();
                    register("gen_id_2", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1222)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.If_format.ElseIf_gen_id_2 obj = get_gen_id_2();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_format.createChannelsElse()");
            }

            public boolean hasElseIf() {
                return true;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1096)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "format";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1097)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1136)
                Type test = ((get_msi_format().get().compareTo(new TypeString("SHORT")) == 0)?TypeInt.True:TypeInt.False);
                return test;
            }

            public M4D.File_main.If_format._param_sample_format_1 get_sample_format_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_sample_format_1 == null) {
                    get_sample_format_1 = new M4D.File_main.If_format._param_sample_format_1();
                }
                return get_sample_format_1;
            }

            public M4D.File_main.If_format.ElseIf_gen_id_2 get_gen_id_2() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1218)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_gen_id_2 == null) {
                    get_gen_id_2 = new M4D.File_main.If_format.ElseIf_gen_id_2();
                }
                return get_gen_id_2;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1210)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1195)
             * parent paramClass=_param_sample_format_1
             * 
             */
            public class ElseIf_gen_id_2
                extends ConditionalClass.ElseIfBranchClass
            {

                M4D.File_main.If_format.ElseIf_gen_id_2 ._param_sample_format_1 get_sample_format_1 = null;

                public boolean hasElseIf() {
                    return false;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    register("sample_format_1", get_sample_format_1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createParamsElseIf()");
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createChannelsElseIf()");
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createParamsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1165)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3 obj = new M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3();
                        register("gen_id_3", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_2.createChannelsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1165)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3 obj = new M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3();
                        obj.createChannels();
                    }
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1196)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                    return "gen_id_2";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1197)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1235)
                    Type test = ((get_msi_format().get().compareTo(new TypeString("LONG")) == 0)?TypeInt.True:TypeInt.False);
                    return test;
                }

                public M4D.File_main.If_format.ElseIf_gen_id_2 ._param_sample_format_1 get_sample_format_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                    if (get_sample_format_1 == null) {
                        get_sample_format_1 = new M4D.File_main.If_format.ElseIf_gen_id_2 ._param_sample_format_1();
                    }
                    return get_sample_format_1;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                 * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1167)
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1150)
                 * parent paramClass=_param_sample_format_1
                 * 
                 */
                public class Else_gen_id_3
                    extends ConditionalClass.ElseBranchClass
                {

                    M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3 ._param_sample_format_1 get_sample_format_1 = null;

                    public void createParams() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1168)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("Else_gen_id_3.createParams()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("sample_format_1", get_sample_format_1());
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1168)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("Else_gen_id_3.createChannels()");
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1151)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                        return "gen_id_3";
                    }

                    public M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3 ._param_sample_format_1 get_sample_format_1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_sample_format_1 == null) {
                            get_sample_format_1 = new M4D.File_main.If_format.ElseIf_gen_id_2 .Else_gen_id_3 ._param_sample_format_1();
                        }
                        return get_sample_format_1;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_sample_format_1
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "sample_format_1";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString(">f4"));
                        }

                    }

                }


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                 * node.type=TypeString
                 * --> nodetype=TypeString
                 * 
                 */
                public class _param_sample_format_1
                    extends Param<TypeString>
                {


                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                        return "sample_format_1";
                    }

                    protected TypeString _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                        // node.type=TypeString
                        // node.expr.type=TypeString
                        // --> nodetype=TypeString
                        return ((TypeString) new TypeString(">i4"));
                    }

                }

            }


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
             * node.type=TypeString
             * --> nodetype=TypeString
             * 
             */
            public class _param_sample_format_1
                extends Param<TypeString>
            {


                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                    return "sample_format_1";
                }

                protected TypeString _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                    // node.type=TypeString
                    // node.expr.type=TypeString
                    // --> nodetype=TypeString
                    return ((TypeString) new TypeString(">i2"));
                }

            }

        }


        /**
         * 
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1110)
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1095)
         * parent paramClass=_param_sample_size_1
         * 
         */
        public class If_size
            extends ConditionalClass
        {

            M4D.File_main.If_size._param_sample_size_1 get_sample_size_1 = null;
            M4D.File_main.If_size.ElseIf_gen_id_0 get_gen_id_0 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                register("sample_size_1", get_sample_size_1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1222)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.If_size.ElseIf_gen_id_0 obj = get_gen_id_0();
                    register("gen_id_0", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1222)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    M4D.File_main.If_size.ElseIf_gen_id_0 obj = get_gen_id_0();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1094)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                log.debug("If_size.createChannelsElse()");
            }

            public boolean hasElseIf() {
                return true;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1096)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "size";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:580)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1097)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1136)
                Type test = ((get_msi_format().get().compareTo(new TypeString("SHORT")) == 0)?TypeInt.True:TypeInt.False);
                return test;
            }

            public M4D.File_main.If_size._param_sample_size_1 get_sample_size_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_sample_size_1 == null) {
                    get_sample_size_1 = new M4D.File_main.If_size._param_sample_size_1();
                }
                return get_sample_size_1;
            }

            public M4D.File_main.If_size.ElseIf_gen_id_0 get_gen_id_0() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1218)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_gen_id_0 == null) {
                    get_gen_id_0 = new M4D.File_main.If_size.ElseIf_gen_id_0();
                }
                return get_gen_id_0;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1210)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1195)
             * parent paramClass=_param_sample_size_1
             * 
             */
            public class ElseIf_gen_id_0
                extends ConditionalClass.ElseIfBranchClass
            {

                M4D.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1 get_sample_size_1 = null;

                public boolean hasElseIf() {
                    return false;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    register("sample_size_1", get_sample_size_1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:296)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createParamsElseIf()");
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:297)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createChannelsElseIf()");
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createParamsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1165)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:257)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1 obj = new M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1();
                        register("gen_id_1", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1215)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:298)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                    log.debug("ElseIf_gen_id_0.createChannelsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1165)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1 obj = new M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1();
                        obj.createChannels();
                    }
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1196)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                    return "gen_id_0";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:620)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1197)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1235)
                    Type test = ((get_msi_format().get().compareTo(new TypeString("LONG")) == 0)?TypeInt.True:TypeInt.False);
                    return test;
                }

                public M4D.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1 get_sample_size_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                    if (get_sample_size_1 == null) {
                        get_sample_size_1 = new M4D.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1();
                    }
                    return get_sample_size_1;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                 * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1167)
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1150)
                 * parent paramClass=_param_sample_size_1
                 * 
                 */
                public class Else_gen_id_1
                    extends ConditionalClass.ElseBranchClass
                {

                    M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1 ._param_sample_size_1 get_sample_size_1 = null;

                    public void createParams() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1168)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:204)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("Else_gen_id_1.createParams()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:517)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:234)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        register("sample_size_1", get_sample_size_1());
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1149)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1168)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:227)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:216)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1809)
                        log.debug("Else_gen_id_1.createChannels()");
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:653)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1151)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                        return "gen_id_1";
                    }

                    public M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1 ._param_sample_size_1 get_sample_size_1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:954)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_sample_size_1 == null) {
                            get_sample_size_1 = new M4D.File_main.If_size.ElseIf_gen_id_0 .Else_gen_id_1 ._param_sample_size_1();
                        }
                        return get_sample_size_1;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_sample_size_1
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                            return "sample_size_1";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                            // node.type=TypeInt
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) new TypeInt(4));
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                            if (get_p == null) {
                                get_p = this.get().safeLongValue();
                            }
                            return get_p;
                        }

                    }

                }


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
                 * node.type=TypeInt
                 * --> nodetype=TypeInt
                 * 
                 */
                public class _param_sample_size_1
                    extends Param<TypeInt>
                {

                    Long get_p = null;

                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                        return "sample_size_1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(4));
                    }

                    public Long get_p() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                        // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                        if (get_p == null) {
                            get_p = this.get().safeLongValue();
                        }
                        return get_p;
                    }

                }

            }


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
             * node.type=TypeInt
             * --> nodetype=TypeInt
             * 
             */
            public class _param_sample_size_1
                extends Param<TypeInt>
            {

                Long get_p = null;

                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                    return "sample_size_1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                    // node.type=TypeInt
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(2));
                }

                public Long get_p() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                    // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                    if (get_p == null) {
                        get_p = this.get().safeLongValue();
                    }
                    return get_p;
                }

            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_calibration_gain
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "calibration_gain";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeFloat
                // node.expr.type=TypeFloat
                // --> nodetype=TypeFloat
                return ((TypeFloat) new TypeFloat(1.0E15D));
            }

            public Double get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_channel_name
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "channel_name";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("ch000name"));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_channel_type_name
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "channel_type_name";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("ch000type"));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_data_filename
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "data_filename";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeString
                // node._read_type=TypeString
                // --> nodetype=TypeString
                // format=("|S2")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.FileDescriptor: (.+;){4}([a-zA-Z0-9,._-]+)")
                // pattern.type=TypeString
                // group=(2)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.FileDescriptor: (.+;){4}([a-zA-Z0-9,._-]+)");
                TypeInt group = ((TypeInt) new TypeInt(2));
                TextBuffer textBuf = textBuffer();
                TypeString _t = null;
                TypeString value = textBuf.read(line, pattern, group, _t);
                return value;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_meg_chan_index
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "meg_chan_index";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeString
                // node._read_type=TypeString
                // --> nodetype=TypeString
                // format=("|S2")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.MegChanIndex: ([0-9,]+)$")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.MegChanIndex: ([0-9,]+)$");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeString _t = null;
                TypeString value = textBuf.read(line, pattern, group, _t);
                return value;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_msi_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "msi_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeString
                // node._read_type=TypeString
                // --> nodetype=TypeString
                // format=("|S1")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.Format: ([a-zA-Z]+)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.Format: ([a-zA-Z]+)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeString _t = null;
                TypeString value = textBuf.read(line, pattern, group, _t);
                return value;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.MegChanCount: ([0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.MegChanCount: ([0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_samples
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "number_of_samples";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_slices_per_epoch().get().mul(get_total_epochs().get()));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_sample_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "sample_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) Builtins.str().call(get_format().get().access("sample_format_1")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_size
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "sample_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_size().get().access("sample_size_1"));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeFloat
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:458)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "sample_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:696)
                // node.type=TypeFloat
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:319)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:460)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:705)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_sampling_frequency
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "sampling_frequency";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeFloat
                // node._read_type=TypeFloat
                // --> nodetype=TypeFloat
                // format=("<f4")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.SampleFrequency: ([0-9]+.[0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.SampleFrequency: ([0-9]+.[0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeFloat _t = null;
                TypeFloat value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Double get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_slices_per_epoch
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "slices_per_epoch";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.SlicesPerEpoch: ([0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.SlicesPerEpoch: ([0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_total_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "total_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.TotalChannels: ([0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.TotalChannels: ([0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:488)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:510)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_total_epochs
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:489)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:525)
                return "total_epochs";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:609)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("MSI.TotalEpochs: ([0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("MSI.TotalEpochs: ([0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:289)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:490)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:639)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:737)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:964)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }

    }

}