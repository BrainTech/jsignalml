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
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:175)
 * 
 */
public class New4DAscii
    extends Signalml
{

    final static Logger log = new Logger(New4DAscii.class);
    private int channelCounter = 0;
    New4DAscii.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("New4DAscii.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            New4DAscii.File_main obj = get_main();
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
        log.debug("New4DAscii.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            New4DAscii.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
        return "New4DAscii";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tNew4DAscii inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        New4DAscii reader = new New4DAscii();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        ChannelSet channelSet = reader.get_set();
        int nrOfChannels = channelSet.getNumberOfChannels();
        int nrOfChannelsToShow = nrOfChannels;
        System.out.println(("Input file for New4DAscii codec: "+ args[ 0 ]));
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
            String channelType = channel.getChannelType();
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

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:186)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:421)
        return null;
    }

    public String getFormatID() {
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

    public New4DAscii.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:932)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
        if (get_main == null) {
            get_main = new New4DAscii.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:873)
     * parent paramClass=_param_number_of_blocks
     * parent paramClass=_param_data_filename
     * parent paramClass=_param_file_type
     * parent paramClass=_param_sampling_frequency
     * parent paramClass=_param_data_format
     * parent paramClass=_param_total_number_of_channels
     * parent paramClass=_param_MEG_channel_group
     * parent paramClass=_param_line_pattern_for_channel_sensitivities
     * parent paramClass=_param_line_number_with_channel_sensitivities
     * parent paramClass=_param_longest_epoch_nr
     * parent paramClass=_param_number_of_samples
     * parent paramClass=_param_msi_format
     * parent paramClass=_param_sample_size
     * parent paramClass=_param_sample_format
     * parent paramClass=_param_sample_unit
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        New4DAscii.File_main._param_number_of_blocks get_number_of_blocks = null;
        New4DAscii.File_main._param_data_filename get_data_filename = null;
        New4DAscii.File_main._param_file_type get_file_type = null;
        New4DAscii.File_main._param_sampling_frequency get_sampling_frequency = null;
        New4DAscii.File_main._param_data_format get_data_format = null;
        New4DAscii.File_main._param_total_number_of_channels get_total_number_of_channels = null;
        New4DAscii.File_main._param_MEG_channel_group get_MEG_channel_group = null;
        New4DAscii.File_main._param_line_pattern_for_channel_sensitivities get_line_pattern_for_channel_sensitivities = null;
        New4DAscii.File_main._param_line_number_with_channel_sensitivities get_line_number_with_channel_sensitivities = null;
        New4DAscii.File_main._param_longest_epoch_nr get_longest_epoch_nr = null;
        New4DAscii.File_main._param_number_of_samples get_number_of_samples = null;
        New4DAscii.File_main._param_msi_format get_msi_format = null;
        New4DAscii.File_main.If_size get_size = null;
        New4DAscii.File_main.If_format get_format = null;
        New4DAscii.File_main._param_sample_size get_sample_size = null;
        New4DAscii.File_main._param_sample_format get_sample_format = null;
        New4DAscii.File_main._param_sample_unit get_sample_unit = null;
        New4DAscii.File_main.File_data_file get_data_file = null;
        New4DAscii.File_main.ChannelSet_data_test get_data_test = null;

        public File_main() {
            isBinary = false;
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:914)
            return super.access(name);
        }

        public void register(String name, Context child) {
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
            register("number_of_blocks", get_number_of_blocks());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_filename", get_data_filename());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("file_type", get_file_type());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sampling_frequency", get_sampling_frequency());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("data_format", get_data_format());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("total_number_of_channels", get_total_number_of_channels());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("MEG_channel_group", get_MEG_channel_group());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("line_pattern_for_channel_sensitivities", get_line_pattern_for_channel_sensitivities());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("line_number_with_channel_sensitivities", get_line_number_with_channel_sensitivities());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("longest_epoch_nr", get_longest_epoch_nr());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("number_of_samples", get_number_of_samples());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("msi_format", get_msi_format());
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.If_size obj = get_size();
                register("size", obj);
                obj.createParams();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.If_format obj = get_format();
                register("format", obj);
                obj.createParams();
            }
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sample_size", get_sample_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sample_format", get_sample_format());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sample_unit", get_sample_unit());
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.File_data_file obj = get_data_file();
                register("data_file", obj);
                obj.createParams();
            }
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1359)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.ChannelSet_data_test obj = get_data_test();
                register("data_test", obj);
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
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.If_size obj = get_size();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.If_format obj = get_format();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.File_data_file obj = get_data_file();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1359)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                New4DAscii.File_main.ChannelSet_data_test obj = get_data_test();
                obj.createChannels();
            }
            registerChannelSet(get_data_test());
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:859)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
            return "main";
        }

        public New4DAscii.File_main._param_number_of_blocks get_number_of_blocks() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_blocks == null) {
                get_number_of_blocks = new New4DAscii.File_main._param_number_of_blocks();
            }
            return get_number_of_blocks;
        }

        public New4DAscii.File_main._param_data_filename get_data_filename() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_filename == null) {
                get_data_filename = new New4DAscii.File_main._param_data_filename();
            }
            return get_data_filename;
        }

        public New4DAscii.File_main._param_file_type get_file_type() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_file_type == null) {
                get_file_type = new New4DAscii.File_main._param_file_type();
            }
            return get_file_type;
        }

        public New4DAscii.File_main._param_sampling_frequency get_sampling_frequency() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sampling_frequency == null) {
                get_sampling_frequency = new New4DAscii.File_main._param_sampling_frequency();
            }
            return get_sampling_frequency;
        }

        public New4DAscii.File_main._param_data_format get_data_format() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_format == null) {
                get_data_format = new New4DAscii.File_main._param_data_format();
            }
            return get_data_format;
        }

        public New4DAscii.File_main._param_total_number_of_channels get_total_number_of_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_total_number_of_channels == null) {
                get_total_number_of_channels = new New4DAscii.File_main._param_total_number_of_channels();
            }
            return get_total_number_of_channels;
        }

        public New4DAscii.File_main._param_MEG_channel_group get_MEG_channel_group() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_MEG_channel_group == null) {
                get_MEG_channel_group = new New4DAscii.File_main._param_MEG_channel_group();
            }
            return get_MEG_channel_group;
        }

        public New4DAscii.File_main._param_line_pattern_for_channel_sensitivities get_line_pattern_for_channel_sensitivities() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_line_pattern_for_channel_sensitivities == null) {
                get_line_pattern_for_channel_sensitivities = new New4DAscii.File_main._param_line_pattern_for_channel_sensitivities();
            }
            return get_line_pattern_for_channel_sensitivities;
        }

        public New4DAscii.File_main._param_line_number_with_channel_sensitivities get_line_number_with_channel_sensitivities() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_line_number_with_channel_sensitivities == null) {
                get_line_number_with_channel_sensitivities = new New4DAscii.File_main._param_line_number_with_channel_sensitivities();
            }
            return get_line_number_with_channel_sensitivities;
        }

        public New4DAscii.File_main._param_longest_epoch_nr get_longest_epoch_nr() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_longest_epoch_nr == null) {
                get_longest_epoch_nr = new New4DAscii.File_main._param_longest_epoch_nr();
            }
            return get_longest_epoch_nr;
        }

        public New4DAscii.File_main._param_number_of_samples get_number_of_samples() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_samples == null) {
                get_number_of_samples = new New4DAscii.File_main._param_number_of_samples();
            }
            return get_number_of_samples;
        }

        public New4DAscii.File_main._param_msi_format get_msi_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_msi_format == null) {
                get_msi_format = new New4DAscii.File_main._param_msi_format();
            }
            return get_msi_format;
        }

        public New4DAscii.File_main.If_size get_size() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1154)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_size == null) {
                get_size = new New4DAscii.File_main.If_size();
            }
            return get_size;
        }

        public New4DAscii.File_main.If_format get_format() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1154)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_format == null) {
                get_format = new New4DAscii.File_main.If_format();
            }
            return get_format;
        }

        public New4DAscii.File_main._param_sample_size get_sample_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_size == null) {
                get_sample_size = new New4DAscii.File_main._param_sample_size();
            }
            return get_sample_size;
        }

        public New4DAscii.File_main._param_sample_format get_sample_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_format == null) {
                get_sample_format = new New4DAscii.File_main._param_sample_format();
            }
            return get_sample_format;
        }

        public New4DAscii.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_unit == null) {
                get_sample_unit = new New4DAscii.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public New4DAscii.File_main.File_data_file get_data_file() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:932)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_file == null) {
                get_data_file = new New4DAscii.File_main.File_data_file();
            }
            return get_data_file;
        }

        public New4DAscii.File_main.ChannelSet_data_test get_data_test() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1356)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data_test == null) {
                get_data_test = new New4DAscii.File_main.ChannelSet_data_test();
            }
            return get_data_test;
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
        public class ChannelSet_data_test
            extends ChannelSetClass
        {

            New4DAscii.File_main.ChannelSet_data_test.Loop_channels get_channels = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1353)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ChannelSet_data_test.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    register("channels", obj);
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
                log.debug("ChannelSet_data_test.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1338)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_test";
            }

            public New4DAscii.File_main.ChannelSet_data_test.Loop_channels get_channels() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1063)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_channels == null) {
                    get_channels = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels();
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

                public String id() {
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
                    TypeList range = ((TypeList) Builtins.range().call(get_total_number_of_channels().get()));
                    return range;
                }

                protected New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1304)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1093)
                    return new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner(((TypeInt) index));
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
                 * parent paramClass=_param_line_nr_channel_sensitivities
                 * parent paramClass=_param_channel_group
                 * 
                 */
                public class channels_inner
                    extends OuterLoopClass.LoopClass
                {

                    final New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.line_nr index;
                    New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_line_nr_channel_sensitivities get_line_nr_channel_sensitivities = null;
                    New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_group get_channel_group = null;
                    New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 get_gen_id_6 = null;

                    channels_inner(TypeInt line_nr) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1302)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1322)
                        this.index = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.line_nr(line_nr);
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
                        register("line_nr", this.index);
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("line_nr_channel_sensitivities", get_line_nr_channel_sensitivities());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_group", get_channel_group());
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 obj = get_gen_id_6();
                            register("gen_id_6", obj);
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
                        log.debug("channels_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1162)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 obj = get_gen_id_6();
                            obj.createChannels();
                        }
                    }

                    public String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1045)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "channels";
                    }

                    public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.line_nr get_line_nr() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1025)
                        return this.index;
                    }

                    public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_line_nr_channel_sensitivities get_line_nr_channel_sensitivities() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_line_nr_channel_sensitivities == null) {
                            get_line_nr_channel_sensitivities = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_line_nr_channel_sensitivities();
                        }
                        return get_line_nr_channel_sensitivities;
                    }

                    public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_group get_channel_group() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_group == null) {
                            get_channel_group = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_group();
                        }
                        return get_channel_group;
                    }

                    public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 get_gen_id_6() {
                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1154)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_gen_id_6 == null) {
                            get_gen_id_6 = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6();
                        }
                        return get_gen_id_6;
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
                     * parent paramClass=_param_channel_name
                     * parent paramClass=_param_channel_type
                     * parent paramClass=_param_channel_sensitivity
                     * parent paramClass=_param_channel_sensitivity_unit
                     * parent paramClass=_param_unit
                     * parent paramClass=_param_calibration_gain
                     * 
                     */
                    public class If_gen_id_6
                        extends ConditionalClass
                    {

                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_name get_channel_name = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_type get_channel_type = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity get_channel_sensitivity = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity_unit get_channel_sensitivity_unit = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_unit get_unit = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_calibration_gain get_calibration_gain = null;
                        New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel get_channel = null;

                        public void createParamsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_gen_id_6.createParamsIf()");
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
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
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("channel_sensitivity", get_channel_sensitivity());
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("channel_sensitivity_unit", get_channel_sensitivity_unit());
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("unit", get_unit());
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("calibration_gain", get_calibration_gain());
                            {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1408)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel obj = get_channel();
                                register("channel", obj);
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
                            log.debug("If_gen_id_6.createChannelsIf()");
                            {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1408)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel obj = get_channel();
                                obj.createChannels();
                            }
                            registerChannel(get_channel());
                        }

                        public void createParamsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_gen_id_6.createParamsElseIf()");
                        }

                        public void createChannelsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_gen_id_6.createChannelsElseIf()");
                        }

                        public void createParamsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_gen_id_6.createParamsElse()");
                        }

                        public void createChannelsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_gen_id_6.createChannelsElse()");
                        }

                        public boolean hasElseIf() {
                            return false;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1135)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "gen_id_6";
                        }

                        public Type getCondition() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1136)
                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1175)
                            Type test = ((get_channel_group().get().compareTo(get_MEG_channel_group().get()) == 0)?TypeInt.True:TypeInt.False);
                            return test;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_name get_channel_name() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_channel_name == null) {
                                get_channel_name = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_name();
                            }
                            return get_channel_name;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_type get_channel_type() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_channel_type == null) {
                                get_channel_type = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_type();
                            }
                            return get_channel_type;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity get_channel_sensitivity() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_channel_sensitivity == null) {
                                get_channel_sensitivity = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity();
                            }
                            return get_channel_sensitivity;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity_unit get_channel_sensitivity_unit() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_channel_sensitivity_unit == null) {
                                get_channel_sensitivity_unit = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_channel_sensitivity_unit();
                            }
                            return get_channel_sensitivity_unit;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_unit get_unit() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_unit == null) {
                                get_unit = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_unit();
                            }
                            return get_unit;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_calibration_gain get_calibration_gain() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_calibration_gain == null) {
                                get_calibration_gain = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 ._param_calibration_gain();
                            }
                            return get_calibration_gain;
                        }

                        public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel get_channel() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1405)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_channel == null) {
                                get_channel = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel();
                            }
                            return get_channel;
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
                         * parent paramClass=_param_mapping
                         * 
                         */
                        public class Channel_channel
                            extends ChannelClass
                        {

                            private int channelNum = channelCounter ++;
                            New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel._param_mapping get_mapping = null;

                            public void createParams() {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1402)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Channel_channel.createParams()");
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                register("mapping", get_mapping());
                            }

                            public void createChannels() {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                                // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1402)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Channel_channel.createChannels()");
                            }

                            public String id() {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1373)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "channel";
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
                                TypeString value = get_sample_format().get();
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
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1378)
                                // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1537)
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

                            public String getChannelName() {
                                // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1382)
                                // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1693)
                                Type value = get_channel_name().get();
                                TypeString stringValue = ((TypeString) value);
                                String strValue = stringValue.getValue();
                                if (strValue.equals("")) {
                                    return ("L"+ channelNum);
                                }
                                return strValue;
                            }

                            public String getChannelType() {
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

                            public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel._param_mapping get_mapping() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                                if (get_mapping == null) {
                                    get_mapping = new New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel._param_mapping();
                                }
                                return get_mapping;
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


                                public String id() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                    return "mapping";
                                }

                                public New4DAscii.File_main.ChannelSet_data_test.Loop_channels.channels_inner.If_gen_id_6 .Channel_channel._param_mapping get() {
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
                                    // node.expr=(sample * (total_number_of_channels + 1) + line_nr + 1)
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return ((TypeInt) sample.mul(get_total_number_of_channels().get().add(new TypeInt(1))).add(get_line_nr().get()).add(new TypeInt(1)));
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
                                    // node.expr=(sample * (total_number_of_channels + 1) + line_nr + 1)
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return (((sample*(get_total_number_of_channels().get_p()+ 1))+ get_line_nr().get_p())+ 1);
                                }

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

                            public String id() {
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
                                return ((TypeFloat) get_channel_sensitivity().get());
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
                         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeString
                         * --> nodetype=TypeString
                         * 
                         */
                        public class _param_channel_name
                            extends Param<TypeString>
                        {


                            public String id() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "channel_name";
                            }

                            protected TypeString _get() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                                // node.type=TypeString
                                // node._read_type=unknown
                                // --> nodetype=TypeString
                                // line=(line_nr_channel_sensitivities)
                                // line.type=unknown
                                // pattern=(line_pattern_for_channel_sensitivities)
                                // pattern.type=TypeString
                                // group=(1)
                                // group.type=TypeInt
                                TypeInt line = ((TypeInt) get_line_nr_channel_sensitivities().get());
                                TypeString pattern = get_line_pattern_for_channel_sensitivities().get();
                                TypeInt group = ((TypeInt) new TypeInt(1));
                                TextBuffer textBuf = textBuffer();
                                TypeString _t = null;
                                TypeString value = textBuf.read(line, pattern, group, _t);
                                return value;
                            }

                        }


                        /**
                         * 
                         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeFloat
                         * --> nodetype=TypeFloat
                         * 
                         */
                        public class _param_channel_sensitivity
                            extends Param<TypeFloat>
                        {

                            Double get_p = null;

                            public String id() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "channel_sensitivity";
                            }

                            protected TypeFloat _get() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                                // node.type=TypeFloat
                                // node._read_type=unknown
                                // --> nodetype=TypeFloat
                                // line=(line_nr_channel_sensitivities)
                                // line.type=unknown
                                // pattern=(line_pattern_for_channel_sensitivities)
                                // pattern.type=TypeString
                                // group=(3)
                                // group.type=TypeInt
                                TypeInt line = ((TypeInt) get_line_nr_channel_sensitivities().get());
                                TypeString pattern = get_line_pattern_for_channel_sensitivities().get();
                                TypeInt group = ((TypeInt) new TypeInt(3));
                                TextBuffer textBuf = textBuffer();
                                TypeFloat _t = null;
                                TypeFloat value = textBuf.read(line, pattern, group, _t);
                                return value;
                            }

                            public Double get_p() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
                         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                         * node.type=TypeString
                         * --> nodetype=TypeString
                         * 
                         */
                        public class _param_channel_sensitivity_unit
                            extends Param<TypeString>
                        {


                            public String id() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "channel_sensitivity_unit";
                            }

                            protected TypeString _get() {
                                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                                // node.type=TypeString
                                // node._read_type=unknown
                                // --> nodetype=TypeString
                                // line=(line_nr_channel_sensitivities)
                                // line.type=unknown
                                // pattern=(line_pattern_for_channel_sensitivities)
                                // pattern.type=TypeString
                                // group=(4)
                                // group.type=TypeInt
                                TypeInt line = ((TypeInt) get_line_nr_channel_sensitivities().get());
                                TypeString pattern = get_line_pattern_for_channel_sensitivities().get();
                                TypeInt group = ((TypeInt) new TypeInt(4));
                                TextBuffer textBuf = textBuffer();
                                TypeString _t = null;
                                TypeString value = textBuf.read(line, pattern, group, _t);
                                return value;
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


                            public String id() {
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
                                return ((TypeString) new TypeString("MEG"));
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


                            public String id() {
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
                                return ((TypeString) Builtins.str().call(get_channel_sensitivity_unit().get()));
                            }

                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_channel_group
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_group";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                            // node.type=TypeInt
                            // node._read_type=unknown
                            // --> nodetype=TypeInt
                            // line=(line_nr_channel_sensitivities)
                            // line.type=unknown
                            // pattern=(line_pattern_for_channel_sensitivities)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) get_line_nr_channel_sensitivities().get());
                            TypeString pattern = get_line_pattern_for_channel_sensitivities().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
                            TextBuffer textBuf = textBuffer();
                            TypeInt _t = null;
                            TypeInt value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
                     * node.type=TypeInt
                     * --> nodetype=unknown
                     * 
                     */
                    public class _param_line_nr_channel_sensitivities
                        extends Param<Type>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "line_nr_channel_sensitivities";
                        }

                        protected Type _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeInt
                            // node.expr.type=unknown
                            // --> nodetype=unknown
                            return get_line_number_with_channel_sensitivities().get().add(get_line_nr().get());
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
                    public class line_nr
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        line_nr(TypeInt index) {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1297)
                            this.cache = index;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1300)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "line_nr";
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
         * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
         * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:873)
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
                isBinary = false;
            }

            public Type access(String name) {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:914)
                return super.access(name);
            }

            public void register(String name, Context child) {
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
                log.debug("File_data_file.createParams()");
            }

            public void createChannels() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:929)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("File_data_file.createChannels()");
            }

            public String id() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:859)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_file";
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
         * parent paramClass=_param_sample_format_1
         * 
         */
        public class If_format
            extends ConditionalClass
        {

            New4DAscii.File_main.If_format._param_sample_format_1 get_sample_format_1 = null;
            New4DAscii.File_main.If_format.ElseIf_gen_id_3 get_gen_id_3 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                register("sample_format_1", get_sample_format_1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.If_format.ElseIf_gen_id_3 obj = get_gen_id_3();
                    register("gen_id_3", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.If_format.ElseIf_gen_id_3 obj = get_gen_id_3();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_format.createChannelsElse()");
            }

            public boolean hasElseIf() {
                return true;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1135)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "format";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1136)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1175)
                Type test = ((get_msi_format().get().compareTo(new TypeString("bigendian,short")) == 0)?TypeInt.True:TypeInt.False);
                return test;
            }

            public New4DAscii.File_main.If_format._param_sample_format_1 get_sample_format_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_sample_format_1 == null) {
                    get_sample_format_1 = new New4DAscii.File_main.If_format._param_sample_format_1();
                }
                return get_sample_format_1;
            }

            public New4DAscii.File_main.If_format.ElseIf_gen_id_3 get_gen_id_3() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1257)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_gen_id_3 == null) {
                    get_gen_id_3 = new New4DAscii.File_main.If_format.ElseIf_gen_id_3();
                }
                return get_gen_id_3;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1249)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1234)
             * parent paramClass=_param_sample_format_1
             * 
             */
            public class ElseIf_gen_id_3
                extends ConditionalClass.ElseIfBranchClass
            {

                New4DAscii.File_main.If_format.ElseIf_gen_id_3 ._param_sample_format_1 get_sample_format_1 = null;
                New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 get_gen_id_4 = null;

                public boolean hasElseIf() {
                    return true;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    register("sample_format_1", get_sample_format_1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 obj = get_gen_id_4();
                        register("gen_id_4", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 obj = get_gen_id_4();
                        obj.createChannels();
                    }
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsElse()");
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsElse()");
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1235)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                    return "gen_id_3";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1236)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1274)
                    Type test = ((get_msi_format().get().compareTo(new TypeString("bigendian,float")) == 0)?TypeInt.True:TypeInt.False);
                    return test;
                }

                public New4DAscii.File_main.If_format.ElseIf_gen_id_3 ._param_sample_format_1 get_sample_format_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                    if (get_sample_format_1 == null) {
                        get_sample_format_1 = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 ._param_sample_format_1();
                    }
                    return get_sample_format_1;
                }

                public New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 get_gen_id_4() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1257)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                    if (get_gen_id_4 == null) {
                        get_gen_id_4 = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4();
                    }
                    return get_gen_id_4;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                 * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1249)
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1234)
                 * parent paramClass=_param_sample_format_1
                 * 
                 */
                public class ElseIf_gen_id_4
                    extends ConditionalClass.ElseIfBranchClass
                {

                    New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_sample_format_1 get_sample_format_1 = null;

                    public boolean hasElseIf() {
                        return false;
                    }

                    public void createParamsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsIf()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("sample_format_1", get_sample_format_1());
                    }

                    public void createChannelsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsIf()");
                    }

                    public void createParamsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsElseIf()");
                    }

                    public void createChannelsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsElseIf()");
                    }

                    public void createParamsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 obj = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5();
                            register("gen_id_5", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannelsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 obj = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5();
                            obj.createChannels();
                        }
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1235)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "gen_id_4";
                    }

                    public Type getCondition() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1236)
                        // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1274)
                        Type test = ((get_msi_format().get().compareTo(new TypeString("ASCII")) == 0)?TypeInt.True:TypeInt.False);
                        return test;
                    }

                    public New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_sample_format_1 get_sample_format_1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_sample_format_1 == null) {
                            get_sample_format_1 = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_sample_format_1();
                        }
                        return get_sample_format_1;
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
                     * parent paramClass=_param_sample_format_1
                     * 
                     */
                    public class Else_gen_id_5
                        extends ConditionalClass.ElseBranchClass
                    {

                        New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_sample_format_1 get_sample_format_1 = null;

                        public void createParams() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_5.createParams()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("sample_format_1", get_sample_format_1());
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_5.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1190)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "gen_id_5";
                        }

                        public New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_sample_format_1 get_sample_format_1() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_sample_format_1 == null) {
                                get_sample_format_1 = new New4DAscii.File_main.If_format.ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_sample_format_1();
                            }
                            return get_sample_format_1;
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
                        public class _param_sample_format_1
                            extends Param<TypeString>
                        {


                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "sample_format_1";
                            }

                            protected TypeString _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                                // node.type=TypeString
                                // node.expr.type=TypeString
                                // --> nodetype=TypeString
                                return ((TypeString) new TypeString("|S0"));
                            }

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
                    public class _param_sample_format_1
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "sample_format_1";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("|S0"));
                        }

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
                public class _param_sample_format_1
                    extends Param<TypeString>
                {


                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "sample_format_1";
                    }

                    protected TypeString _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                        // node.type=TypeString
                        // node.expr.type=TypeString
                        // --> nodetype=TypeString
                        return ((TypeString) new TypeString(">f4"));
                    }

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
            public class _param_sample_format_1
                extends Param<TypeString>
            {


                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                    return "sample_format_1";
                }

                protected TypeString _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                    // node.type=TypeString
                    // node.expr.type=TypeString
                    // --> nodetype=TypeString
                    return ((TypeString) new TypeString(">i2"));
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
         * parent paramClass=_param_sample_size_1
         * 
         */
        public class If_size
            extends ConditionalClass
        {

            New4DAscii.File_main.If_size._param_sample_size_1 get_sample_size_1 = null;
            New4DAscii.File_main.If_size.ElseIf_gen_id_0 get_gen_id_0 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                register("sample_size_1", get_sample_size_1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.If_size.ElseIf_gen_id_0 obj = get_gen_id_0();
                    register("gen_id_0", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    New4DAscii.File_main.If_size.ElseIf_gen_id_0 obj = get_gen_id_0();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1133)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1151)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_size.createChannelsElse()");
            }

            public boolean hasElseIf() {
                return true;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1135)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "size";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1136)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1175)
                Type test = ((get_msi_format().get().compareTo(new TypeString("bigendian,short")) == 0)?TypeInt.True:TypeInt.False);
                return test;
            }

            public New4DAscii.File_main.If_size._param_sample_size_1 get_sample_size_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_sample_size_1 == null) {
                    get_sample_size_1 = new New4DAscii.File_main.If_size._param_sample_size_1();
                }
                return get_sample_size_1;
            }

            public New4DAscii.File_main.If_size.ElseIf_gen_id_0 get_gen_id_0() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1257)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_gen_id_0 == null) {
                    get_gen_id_0 = new New4DAscii.File_main.If_size.ElseIf_gen_id_0();
                }
                return get_gen_id_0;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1249)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1234)
             * parent paramClass=_param_sample_size_1
             * 
             */
            public class ElseIf_gen_id_0
                extends ConditionalClass.ElseIfBranchClass
            {

                New4DAscii.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1 get_sample_size_1 = null;
                New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 get_gen_id_1 = null;

                public boolean hasElseIf() {
                    return true;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    register("sample_size_1", get_sample_size_1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createParamsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 obj = get_gen_id_1();
                        register("gen_id_1", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createChannelsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1261)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 obj = get_gen_id_1();
                        obj.createChannels();
                    }
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createParamsElse()");
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_0.createChannelsElse()");
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1235)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                    return "gen_id_0";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1236)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1274)
                    Type test = ((get_msi_format().get().compareTo(new TypeString("bigendian,float")) == 0)?TypeInt.True:TypeInt.False);
                    return test;
                }

                public New4DAscii.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1 get_sample_size_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                    if (get_sample_size_1 == null) {
                        get_sample_size_1 = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 ._param_sample_size_1();
                    }
                    return get_sample_size_1;
                }

                public New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 get_gen_id_1() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1257)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                    if (get_gen_id_1 == null) {
                        get_gen_id_1 = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1();
                    }
                    return get_gen_id_1;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                 * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1249)
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1234)
                 * parent paramClass=_param_sample_size_1
                 * 
                 */
                public class ElseIf_gen_id_1
                    extends ConditionalClass.ElseIfBranchClass
                {

                    New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_sample_size_1 get_sample_size_1 = null;

                    public boolean hasElseIf() {
                        return false;
                    }

                    public void createParamsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createParamsIf()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("sample_size_1", get_sample_size_1());
                    }

                    public void createChannelsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createChannelsIf()");
                    }

                    public void createParamsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createParamsElseIf()");
                    }

                    public void createChannelsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createChannelsElseIf()");
                    }

                    public void createParamsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createParamsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 obj = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2();
                            register("gen_id_2", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannelsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1233)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1254)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_1.createChannelsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1204)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 obj = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2();
                            obj.createChannels();
                        }
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1235)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "gen_id_1";
                    }

                    public Type getCondition() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1236)
                        // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1274)
                        Type test = ((get_msi_format().get().compareTo(new TypeString("ASCII")) == 0)?TypeInt.True:TypeInt.False);
                        return test;
                    }

                    public New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_sample_size_1 get_sample_size_1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_sample_size_1 == null) {
                            get_sample_size_1 = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_sample_size_1();
                        }
                        return get_sample_size_1;
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
                     * parent paramClass=_param_sample_size_1
                     * 
                     */
                    public class Else_gen_id_2
                        extends ConditionalClass.ElseBranchClass
                    {

                        New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_sample_size_1 get_sample_size_1 = null;

                        public void createParams() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_2.createParams()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("sample_size_1", get_sample_size_1());
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1188)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1207)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_2.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1190)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "gen_id_2";
                        }

                        public New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_sample_size_1 get_sample_size_1() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_sample_size_1 == null) {
                                get_sample_size_1 = new New4DAscii.File_main.If_size.ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_sample_size_1();
                            }
                            return get_sample_size_1;
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
                        public class _param_sample_size_1
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                                return "sample_size_1";
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_sample_size_1
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "sample_size_1";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeInt
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
                public class _param_sample_size_1
                    extends Param<TypeInt>
                {

                    Long get_p = null;

                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "sample_size_1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(4));
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
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
             * node.type=TypeInt
             * --> nodetype=TypeInt
             * 
             */
            public class _param_sample_size_1
                extends Param<TypeInt>
            {

                Long get_p = null;

                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                    return "sample_size_1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                    // node.type=TypeInt
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(2));
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_MEG_channel_group
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "MEG_channel_group";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*([0-9]*)[ ]+MEG[ ]*channels[,]{1}[ ]*group[ ]*([0-9]*)")
                // pattern.type=TypeString
                // group=(2)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*([0-9]*)[ ]+MEG[ ]*channels[,]{1}[ ]*group[ ]*([0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(2));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_data_filename
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_filename";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) Builtins.fetch().call(Builtins.get_filename().call(get_main().get()), new TypeString("([a-zA-Z0-9_,.;]+.)hdr"), new TypeInt(1)).add(new TypeString("data")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_data_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeString
                // node._read_type=unknown
                // --> nodetype=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*Data[ _]*[Ff]{1}ormat[ ]*:[ ]*([A-Za-z,]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*Data[ _]*[Ff]{1}ormat[ ]*:[ ]*([A-Za-z,]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeString _t = null;
                TypeString value = textBuf.read(line, pattern, group, _t);
                return value;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_file_type
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "file_type";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeString
                // node._read_type=unknown
                // --> nodetype=TypeString
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*File[ _]*[Tt]{1}ype[ ]*:[ ]*'([A-Za-z]*)'")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*File[ _]*[Tt]{1}ype[ ]*:[ ]*'([A-Za-z]*)'");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeString _t = null;
                TypeString value = textBuf.read(line, pattern, group, _t);
                return value;
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
        public class _param_line_number_with_channel_sensitivities
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "line_number_with_channel_sensitivities";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) Builtins.get_line_for_pattern().call(get_main().get(), get_line_pattern_for_channel_sensitivities().get(), new TypeInt(0)));
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
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_line_pattern_for_channel_sensitivities
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "line_pattern_for_channel_sensitivities";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("^[ ]*([A-Za-z0-9]+)[ ]+([0-9]+)[ ]+([0-9e+-.]+)[ ]+([a-zA-Z]+)[ ]*$"));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_longest_epoch_nr
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "longest_epoch_nr";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*Epoch[ _]*([0-9]*)[ ]*:[ ]*([0-9]*)[ ]+slices[,]{1}[ ]*([0-9]+[.]{1}[0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*Epoch[ _]*([0-9]*)[ ]*:[ ]*([0-9]*)[ ]+slices[,]{1}[ ]*([0-9]+[.]{1}[0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_msi_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "msi_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) get_data_format().get());
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
        public class _param_number_of_blocks
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_blocks";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(8));
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_samples
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_samples";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*Epoch[ _]*([0-9]*)[ ]*:[ ]*([0-9]*)[ ]+slices[,]{1}[ ]*([0-9]+[.]{1}[0-9]*)")
                // pattern.type=TypeString
                // group=(2)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*Epoch[ _]*([0-9]*)[ ]*:[ ]*([0-9]*)[ ]+slices[,]{1}[ ]*([0-9]+[.]{1}[0-9]*)");
                TypeInt group = ((TypeInt) new TypeInt(2));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_sample_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "sample_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) Builtins.str().call(get_format().get().access("sample_format_1")));
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
        public class _param_sample_size
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "sample_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_size().get().access("sample_size_1"));
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
         * node.type=TypeFloat
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_unit
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeFloat
         * --> nodetype=TypeFloat
         * 
         */
        public class _param_sampling_frequency
            extends Param<TypeFloat>
        {

            Double get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "sampling_frequency";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeFloat
                // node._read_type=unknown
                // --> nodetype=TypeFloat
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*Sample[ _]*[Ff]{1}requency[ ]*:[ ]*([0-9]+[.0-9e+]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*Sample[ _]*[Ff]{1}requency[ ]*:[ ]*([0-9]+[.0-9e+]*)");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeFloat _t = null;
                TypeFloat value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Double get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_total_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "total_number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:613)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
                // line=(-1)
                // line.type=TypeInt
                // pattern=("[ ]*([0-9]*)[ ]+channels[ ]*$")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("[ ]*([0-9]*)[ ]+channels[ ]*$");
                TypeInt group = ((TypeInt) new TypeInt(1));
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:482)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:635)
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
