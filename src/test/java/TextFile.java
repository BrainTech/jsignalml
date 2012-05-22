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
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:172)
 * 
 */
public class TextFile
    extends Signalml
{

    final static Logger log = new Logger(TextFile.class);
    TextFile.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:177)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
        log.debug("TextFile.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:925)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            TextFile.File_main obj = get_main();
            register("main", obj);
            obj.createParams();
        }
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:177)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
        log.debug("TextFile.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:925)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            TextFile.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
        return "TextFile";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:181)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:291)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tTextFile inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        TextFile reader = new TextFile();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        ChannelSet channelSet = reader.get_set();
        int nrOfChannels = channelSet.getNumberOfChannels();
        int nrOfChannelsToShow = nrOfChannels;
        System.out.println(("Input file for TextFile codec: "+ args[ 0 ]));
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
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:182)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:405)
        return null;
    }

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:414)
        return null;
    }

    public String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:423)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:395)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:186)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:432)
    }

    public TextFile.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:922)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
        if (get_main == null) {
            get_main = new TextFile.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:863)
     * parent paramClass=_param_number_of_channels
     * parent paramClass=_param_sampling_frequency
     * parent paramClass=_param_number_of_samples
     * parent paramClass=_param_meg_chan_index
     * parent paramClass=_param_total_epochs
     * parent paramClass=_param_slices_per_epoch
     * parent paramClass=_param_total_channels
     * parent paramClass=_param_get_data_filename
     * parent paramClass=_param_msi_format
     * parent paramClass=_param_sample_unit
     * parent paramClass=_param_sample_size
     * parent paramClass=_param_sample_format
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        TextFile.File_main._param_number_of_channels get_number_of_channels = null;
        TextFile.File_main._param_sampling_frequency get_sampling_frequency = null;
        TextFile.File_main._param_number_of_samples get_number_of_samples = null;
        TextFile.File_main._param_meg_chan_index get_meg_chan_index = null;
        TextFile.File_main._param_total_epochs get_total_epochs = null;
        TextFile.File_main._param_slices_per_epoch get_slices_per_epoch = null;
        TextFile.File_main._param_total_channels get_total_channels = null;
        TextFile.File_main._param_get_data_filename get_get_data_filename = null;
        TextFile.File_main.File_data_file get_data_file = null;
        TextFile.File_main._param_msi_format get_msi_format = null;
        TextFile.File_main.If_format get_format = null;
        TextFile.File_main._param_sample_unit get_sample_unit = null;
        TextFile.File_main._param_sample_size get_sample_size = null;
        TextFile.File_main._param_sample_format get_sample_format = null;
        TextFile.File_main.ChannelSet_data_test get_data_test = null;

        public File_main() {
            isBinary = false;
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:904)
            return super.access(name);
        }

        public void register(String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:912)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:919)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            log.debug("File_main.createParams()");
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("number_of_channels", get_number_of_channels());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("sampling_frequency", get_sampling_frequency());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("number_of_samples", get_number_of_samples());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("meg_chan_index", get_meg_chan_index());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("total_epochs", get_total_epochs());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("slices_per_epoch", get_slices_per_epoch());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("total_channels", get_total_channels());
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("get_data_filename", get_get_data_filename());
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:925)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.File_data_file obj = get_data_file();
                register("data_file", obj);
                obj.createParams();
            }
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("msi_format", get_msi_format());
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1152)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.If_format obj = get_format();
                register("format", obj);
                obj.createParams();
            }
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("sample_unit", get_sample_unit());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("sample_size", get_sample_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            register("sample_format", get_sample_format());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1349)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.ChannelSet_data_test obj = get_data_test();
                register("data_test", obj);
                obj.createParams();
            }
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:919)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
            log.debug("File_main.createChannels()");
            {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:925)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.File_data_file obj = get_data_file();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1152)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.If_format obj = get_format();
                obj.createChannels();
            }
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1349)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                TextFile.File_main.ChannelSet_data_test obj = get_data_test();
                obj.createChannels();
            }
            registerChannelSet(get_data_test());
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:849)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
            return "main";
        }

        public TextFile.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_number_of_channels == null) {
                get_number_of_channels = new TextFile.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public TextFile.File_main._param_sampling_frequency get_sampling_frequency() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_sampling_frequency == null) {
                get_sampling_frequency = new TextFile.File_main._param_sampling_frequency();
            }
            return get_sampling_frequency;
        }

        public TextFile.File_main._param_number_of_samples get_number_of_samples() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_number_of_samples == null) {
                get_number_of_samples = new TextFile.File_main._param_number_of_samples();
            }
            return get_number_of_samples;
        }

        public TextFile.File_main._param_meg_chan_index get_meg_chan_index() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_meg_chan_index == null) {
                get_meg_chan_index = new TextFile.File_main._param_meg_chan_index();
            }
            return get_meg_chan_index;
        }

        public TextFile.File_main._param_total_epochs get_total_epochs() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_total_epochs == null) {
                get_total_epochs = new TextFile.File_main._param_total_epochs();
            }
            return get_total_epochs;
        }

        public TextFile.File_main._param_slices_per_epoch get_slices_per_epoch() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_slices_per_epoch == null) {
                get_slices_per_epoch = new TextFile.File_main._param_slices_per_epoch();
            }
            return get_slices_per_epoch;
        }

        public TextFile.File_main._param_total_channels get_total_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_total_channels == null) {
                get_total_channels = new TextFile.File_main._param_total_channels();
            }
            return get_total_channels;
        }

        public TextFile.File_main._param_get_data_filename get_get_data_filename() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_get_data_filename == null) {
                get_get_data_filename = new TextFile.File_main._param_get_data_filename();
            }
            return get_get_data_filename;
        }

        public TextFile.File_main.File_data_file get_data_file() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:922)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_data_file == null) {
                get_data_file = new TextFile.File_main.File_data_file();
            }
            return get_data_file;
        }

        public TextFile.File_main._param_msi_format get_msi_format() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_msi_format == null) {
                get_msi_format = new TextFile.File_main._param_msi_format();
            }
            return get_msi_format;
        }

        public TextFile.File_main.If_format get_format() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1144)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_format == null) {
                get_format = new TextFile.File_main.If_format();
            }
            return get_format;
        }

        public TextFile.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_sample_unit == null) {
                get_sample_unit = new TextFile.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public TextFile.File_main._param_sample_size get_sample_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_sample_size == null) {
                get_sample_size = new TextFile.File_main._param_sample_size();
            }
            return get_sample_size;
        }

        public TextFile.File_main._param_sample_format get_sample_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_sample_format == null) {
                get_sample_format = new TextFile.File_main._param_sample_format();
            }
            return get_sample_format;
        }

        public TextFile.File_main.ChannelSet_data_test get_data_test() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1346)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
            if (get_data_test == null) {
                get_data_test = new TextFile.File_main.ChannelSet_data_test();
            }
            return get_data_test;
        }


        /**
         * 
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
         * jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1341)
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1327)
         * 
         */
        public class ChannelSet_data_test
            extends ChannelSetClass
        {

            TextFile.File_main.ChannelSet_data_test.Loop_channels get_channels = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1343)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("ChannelSet_data_test.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1057)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    TextFile.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    register("channels", obj);
                    obj.createParams();
                }
            }

            public void createChannels() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1326)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1343)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("ChannelSet_data_test.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1057)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    TextFile.File_main.ChannelSet_data_test.Loop_channels obj = get_channels();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1328)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "data_test";
            }

            public TextFile.File_main.ChannelSet_data_test.Loop_channels get_channels() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1053)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_channels == null) {
                    get_channels = new TextFile.File_main.ChannelSet_data_test.Loop_channels();
                }
                return get_channels;
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1054)
            }


            /**
             * 
             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1029)
             * 
             */
            public class Loop_channels
                extends OuterLoopClass
            {


                public void createParams() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1050)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    log.debug("Loop_channels.createParams()");
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1028)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1050)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    log.debug("Loop_channels.createChannels()");
                }

                public String id() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1030)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                    return "channels";
                }

                protected TypeList getSequence() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1031)
                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:1066)
                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                    return range;
                }

                protected TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1294)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1083)
                    return new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner(((Type) index));
                }


                /**
                 * 
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1033)
                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1102)
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1034)
                 * parent paramClass=_param_channel_name
                 * parent paramClass=_param_single_channel_name_pattern
                 * parent paramClass=_param_channel_index
                 * parent paramClass=_param_single_channel_index_pattern
                 * parent paramClass=_param_mapping
                 * parent paramClass=_param_calibration_gain
                 * parent paramClass=_param_unit
                 * 
                 */
                public class channels_inner
                    extends OuterLoopClass.LoopClass
                {

                    final TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index index;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_1 get_gen_id_1 = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern get_single_channel_name_pattern = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index get_channel_index = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern get_single_channel_index_pattern = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get_mapping = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain = null;
                    TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_unit get_unit = null;

                    channels_inner(Type index) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1292)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1312)
                        this.index = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index(index);
                    }

                    public void createParams() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1033)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1104)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        log.debug("channels_inner.createParams()");
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1008)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1019)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("index", this.index);
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1398)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                            TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_1 obj = get_gen_id_1();
                            register("gen_id_1", obj);
                            obj.createParams();
                        }
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("channel_name", get_channel_name());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("single_channel_name_pattern", get_single_channel_name_pattern());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("channel_index", get_channel_index());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("single_channel_index_pattern", get_single_channel_index_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("mapping", get_mapping());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("calibration_gain", get_calibration_gain());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        register("unit", get_unit());
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1033)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1104)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                        // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                        log.debug("channels_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1398)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                            TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_1 obj = get_gen_id_1();
                            obj.createChannels();
                        }
                        registerChannel(get_gen_id_1());
                    }

                    public String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1035)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                        return "channels";
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.index get_index() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1008)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1015)
                        return this.index;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_1 get_gen_id_1() {
                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1395)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_gen_id_1 == null) {
                            get_gen_id_1 = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner.Channel_gen_id_1();
                        }
                        return get_gen_id_1;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_channel_name == null) {
                            get_channel_name = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_name();
                        }
                        return get_channel_name;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern get_single_channel_name_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_single_channel_name_pattern == null) {
                            get_single_channel_name_pattern = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_name_pattern();
                        }
                        return get_single_channel_name_pattern;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index get_channel_index() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_channel_index == null) {
                            get_channel_index = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_channel_index();
                        }
                        return get_channel_index;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern get_single_channel_index_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_single_channel_index_pattern == null) {
                            get_single_channel_index_pattern = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_single_channel_index_pattern();
                        }
                        return get_single_channel_index_pattern;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get_mapping() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_mapping == null) {
                            get_mapping = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping();
                        }
                        return get_mapping;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_calibration_gain == null) {
                            get_calibration_gain = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_calibration_gain();
                        }
                        return get_calibration_gain;
                    }

                    public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_unit get_unit() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                        if (get_unit == null) {
                            get_unit = new TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_unit();
                        }
                        return get_unit;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1390)
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1361)
                     * 
                     */
                    public class Channel_gen_id_1
                        extends ChannelClass
                    {


                        public void createParams() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1392)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                            log.debug("Channel_gen_id_1.createParams()");
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1360)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1392)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                            // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                            log.debug("Channel_gen_id_1.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1363)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "gen_id_1";
                        }

                        protected MyBuffer _buffer() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1364)
                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1407)
                            return buffer();
                        }

                        public TypeString getSampleFormat() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1365)
                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1419)
                            // node.format.type=TypeString
                            TypeString value = get_sample_format().get();
                            return ((TypeString) value);
                        }

                        public TypeInt mapSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1366)
                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1432)
                            Type value = get_mapping().get();
                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                        }

                        public float getSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1367)
                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1447)
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
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1368)
                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1524)
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
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1369)
                            // jsignalml.JavaClassGen.applyLinearTransformationMethod(JavaClassGen.java:1608)
                            float calibGain = getCalibrationGain().getValue().floatValue();
                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                            float sampleUnit = getSampleUnit().getValue().floatValue();
                            return (((rawValue-calibOffs)*calibGain)*sampleUnit);
                        }

                        public double getSamplingFrequency() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1641)
                            Type value = get_sampling_frequency().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast.getValue();
                        }

                        public long getNumberOfSamples() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1371)
                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1654)
                            Type value = get_number_of_samples().get();
                            TypeInt cast = TypeInt.I.make(value);
                            return cast.safeLongValue();
                        }

                        public String getChannelName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1372)
                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1680)
                            Type value = get_channel_name().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public String getChannelTypeName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1373)
                            // jsignalml.JavaClassGen.getChannelTypeNameMethod(JavaClassGen.java:1708)
                            Type value = get_channel_type_name().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public TypeFloat getCalibrationGain() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1374)
                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1724)
                            Type value = get_calibration_gain().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getCalibrationOffset() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1375)
                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1740)
                            Type value = get_calibration_offset().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getSampleUnit() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1376)
                            // jsignalml.JavaClassGen.getSampleUnitMethod(JavaClassGen.java:1668)
                            Type value = get_sample_unit().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeFloat
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_calibration_gain
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "calibration_gain";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                            // node.type=TypeFloat
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("1.0e15f"));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_channel_index
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "channel_index";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                            // node.type=TypeInt
                            // node._read_type=unknown
                            // --> nodetype=TypeInt
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
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                            if (get_p == null) {
                                get_p = this.get().safeLongValue();
                            }
                            return get_p;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_name
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "channel_name";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                            // node.type=TypeString
                            // node._read_type=unknown
                            // --> nodetype=TypeString
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_mapping
                        extends FunctionParam<TypeInt>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "mapping";
                        }

                        public TextFile.File_main.ChannelSet_data_test.Loop_channels.channels_inner._param_mapping get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:447)
                            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:744)
                            return this;
                        }

                        public TypeInt call(TypeInt sample) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:448)
                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:774)
                            // node.type=TypeInt
                            // node.expr=((sample * total_channels + channel_index - 1) * sample_size)
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) sample.mul(get_total_channels().get()).add(get_channel_index().get()).sub(new TypeInt(1)).mul(get_sample_size().get()));
                        }

                        public TypeInt call(List<Type> args) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:448)
                            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:784)
                            if (args.size()!= 1) {
                                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
                            }
                            return this.call(((TypeInt) args.get(0)));
                        }

                        public long call_p(long sample) {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:822)
                            // node.type=TypeInt
                            // node.expr=((sample * total_channels + channel_index - 1) * sample_size)
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((((sample*get_total_channels().get_p())+ get_channel_index().get_p())- 1)*get_sample_size().get_p());
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_single_channel_index_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "single_channel_index_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("MSI.MegChanIndex: ([0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_single_channel_name_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "single_channel_name_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("MSI.MegChanNames: ([a-zA-Z0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([A-Za-z0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_unit
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "unit";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("\u03bcV"));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                     * jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1283)
                     * 
                     */
                    public class index
                        extends Param<Type>
                    {


                        index(Type index) {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1287)
                            this.cache = index;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1290)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                            return "index";
                        }

                        protected Type _get() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1007)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1297)
                            throw new RuntimeException();
                        }

                    }

                }

            }

        }


        /**
         * 
         * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
         * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:863)
         * 
         */
        public class File_data_file
            extends Signalml.FileClass
        {


            public File_data_file() {
                File main = default_filename;
                Integer endIndex = (main.getAbsolutePath().length()-main.getName().length());
                String dirname = main.getAbsolutePath().substring(0, endIndex);
                String filename = (dirname + get_get_data_filename().get().value);
                currentFilename = new File(filename);
            }

            public Type access(String name) {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:904)
                return super.access(name);
            }

            public void register(String name, Context child) {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:912)
                super.register(name, child);
            }

            public void createParams() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:919)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("File_data_file.createParams()");
            }

            public void createChannels() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:848)
                // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:919)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("File_data_file.createChannels()");
            }

            public String id() {
                // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:849)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "data_file";
            }

        }


        /**
         * 
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1139)
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1124)
         * parent paramClass=_param_sample_size_1
         * parent paramClass=_param_sample_format_1
         * 
         */
        public class If_format
            extends ConditionalClass
        {

            TextFile.File_main.If_format._param_sample_size_1 get_sample_size_1 = null;
            TextFile.File_main.If_format._param_sample_format_1 get_sample_format_1 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:281)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                register("sample_size_1", get_sample_size_1());
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                register("sample_format_1", get_sample_format_1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:281)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:282)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createParamsElseIf()");
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:282)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createChannelsElseIf()");
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:283)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createParamsElse()");
                {
                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1178)
                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:242)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    TextFile.File_main.If_format.Else_gen_id_0 obj = new TextFile.File_main.If_format.Else_gen_id_0();
                    register("gen_id_0", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1123)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1141)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:283)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                log.debug("If_format.createChannelsElse()");
                {
                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1178)
                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1194)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    TextFile.File_main.If_format.Else_gen_id_0 obj = new TextFile.File_main.If_format.Else_gen_id_0();
                    obj.createChannels();
                }
            }

            public boolean hasElseIf() {
                return false;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1125)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "format";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1126)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1165)
                Type test = ((get_msi_format().get().compareTo(new TypeString("SHORT")) == 0)?TypeInt.True:TypeInt.False);
                return test;
            }

            public TextFile.File_main.If_format._param_sample_size_1 get_sample_size_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_sample_size_1 == null) {
                    get_sample_size_1 = new TextFile.File_main.If_format._param_sample_size_1();
                }
                return get_sample_size_1;
            }

            public TextFile.File_main.If_format._param_sample_format_1 get_sample_format_1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_sample_format_1 == null) {
                    get_sample_format_1 = new TextFile.File_main.If_format._param_sample_format_1();
                }
                return get_sample_format_1;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1178)
             * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1196)
             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1179)
             * parent paramClass=_param_sample_size_1
             * parent paramClass=_param_sample_format_1
             * 
             */
            public class Else_gen_id_0
                extends ConditionalClass.ElseBranchClass
            {

                TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_size_1 get_sample_size_1 = null;
                TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_format_1 get_sample_format_1 = null;

                public void createParams() {
                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1178)
                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1197)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:203)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    log.debug("Else_gen_id_0.createParams()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    register("sample_size_1", get_sample_size_1());
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:233)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    register("sample_format_1", get_sample_format_1());
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1178)
                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1197)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:226)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:215)
                    // jsignalml.JavaClassGen.access$13(JavaClassGen.java:1835)
                    log.debug("Else_gen_id_0.createChannels()");
                }

                public String id() {
                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1180)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                    return "gen_id_0";
                }

                public TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_size_1 get_sample_size_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                    if (get_sample_size_1 == null) {
                        get_sample_size_1 = new TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_size_1();
                    }
                    return get_sample_size_1;
                }

                public TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_format_1 get_sample_format_1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:511)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:983)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                    if (get_sample_format_1 == null) {
                        get_sample_format_1 = new TextFile.File_main.If_format.Else_gen_id_0 ._param_sample_format_1();
                    }
                    return get_sample_format_1;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
                 * node.type=TypeString
                 * --> nodetype=TypeString
                 * 
                 */
                public class _param_sample_format_1
                    extends Param<TypeString>
                {


                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                        return "sample_format_1";
                    }

                    protected TypeString _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                        // node.type=TypeString
                        // node.expr.type=TypeString
                        // --> nodetype=TypeString
                        return ((TypeString) new TypeString(">f4"));
                    }

                }


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
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
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                        return "sample_size_1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(4));
                    }

                    public Long get_p() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:731)
                        // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
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
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
             * node.type=TypeString
             * --> nodetype=TypeString
             * 
             */
            public class _param_sample_format_1
                extends Param<TypeString>
            {


                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                    return "sample_format_1";
                }

                protected TypeString _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                    // node.type=TypeString
                    // node.expr.type=TypeString
                    // --> nodetype=TypeString
                    return ((TypeString) new TypeString(">i2"));
                }

            }


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
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
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                    return "sample_size_1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                    // node.type=TypeInt
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(2));
                }

                public Long get_p() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:731)
                    // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
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
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_get_data_filename
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "get_data_filename";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeString
                // node._read_type=unknown
                // --> nodetype=TypeString
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_meg_chan_index
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "meg_chan_index";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeString
                // node._read_type=unknown
                // --> nodetype=TypeString
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_msi_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "msi_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeString
                // node._read_type=unknown
                // --> nodetype=TypeString
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
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
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_samples
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "number_of_samples";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_slices_per_epoch().get().mul(get_total_epochs().get()));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:731)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_sample_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "sample_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) Builtins.str().call(get_format().get().access("sample_format_1")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
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
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "sample_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) get_format().get().access("sample_size_1"));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:731)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:442)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
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
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:443)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "sample_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:722)
                // node.type=TypeFloat
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:445)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:731)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
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
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "sampling_frequency";
            }

            protected TypeFloat _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeFloat
                // node._read_type=unknown
                // --> nodetype=TypeFloat
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
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().getValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_slices_per_epoch
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "slices_per_epoch";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
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
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_total_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "total_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
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
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }


        /**
         * 
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:473)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:507)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_total_epochs
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:474)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:522)
                return "total_epochs";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:606)
                // node.type=TypeInt
                // node._read_type=unknown
                // --> nodetype=TypeInt
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
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:475)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:628)
                // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:766)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:993)
                if (get_p == null) {
                    get_p = this.get().safeLongValue();
                }
                return get_p;
            }

        }

    }

}
