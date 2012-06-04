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
public class ALT
    extends Signalml
{

    final static Logger log = new Logger(ALT.class);
    private int channelCounter = 0;
    ALT.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("ALT.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            ALT.File_main obj = get_main();
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
        log.debug("ALT.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            ALT.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
        return "ALT";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tALT inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        ALT reader = new ALT();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        ChannelSet channelSet = reader.get_set();
        int nrOfChannels = channelSet.getNumberOfChannels();
        int nrOfChannelsToShow = nrOfChannels;
        System.out.println(("Input file for ALT codec: "+ args[ 0 ]));
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

    public ALT.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:932)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
        if (get_main == null) {
            get_main = new ALT.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:873)
     * parent paramClass=_param_number_of_channels
     * parent paramClass=_param_sample_size
     * parent paramClass=_param_sample_format
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        ALT.File_main._param_number_of_channels get_number_of_channels = null;
        ALT.File_main._param_sample_size get_sample_size = null;
        ALT.File_main._param_sample_format get_sample_format = null;
        ALT.File_main.ChannelSet_data get_data = null;

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
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
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
            register("sample_size", get_sample_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("sample_format", get_sample_format());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1359)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                ALT.File_main.ChannelSet_data obj = get_data();
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
                ALT.File_main.ChannelSet_data obj = get_data();
                obj.createChannels();
            }
            registerChannelSet(get_data());
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:859)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
            return "main";
        }

        public ALT.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_number_of_channels == null) {
                get_number_of_channels = new ALT.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public ALT.File_main._param_sample_size get_sample_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_size == null) {
                get_sample_size = new ALT.File_main._param_sample_size();
            }
            return get_sample_size;
        }

        public ALT.File_main._param_sample_format get_sample_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_sample_format == null) {
                get_sample_format = new ALT.File_main._param_sample_format();
            }
            return get_sample_format;
        }

        public ALT.File_main.ChannelSet_data get_data() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1336)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1356)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
            if (get_data == null) {
                get_data = new ALT.File_main.ChannelSet_data();
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

            ALT.File_main.ChannelSet_data.Loop_channels get_channels = null;

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
                    ALT.File_main.ChannelSet_data.Loop_channels obj = get_channels();
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
                log.debug("ChannelSet_data.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1067)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    ALT.File_main.ChannelSet_data.Loop_channels obj = get_channels();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:142)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1338)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "data";
            }

            public ALT.File_main.ChannelSet_data.Loop_channels get_channels() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1038)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:1063)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                if (get_channels == null) {
                    get_channels = new ALT.File_main.ChannelSet_data.Loop_channels();
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
                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                    return range;
                }

                protected ALT.File_main.ChannelSet_data.Loop_channels.channels_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1304)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:1093)
                    return new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner(((TypeInt) index));
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
                 * parent paramClass=_param_channel_name_pattern
                 * parent paramClass=_param_channel_type_name_pattern
                 * parent paramClass=_param_channel_filename_pattern
                 * parent paramClass=_param_channel_sampling_frequency_pattern
                 * parent paramClass=_param_channel_nr_of_samples_pattern
                 * parent paramClass=_param_calibration_gain_pattern
                 * parent paramClass=_param_calibration_offset_pattern
                 * parent paramClass=_param_channel_name
                 * parent paramClass=_param_channel_type
                 * parent paramClass=_param_channel_filename
                 * parent paramClass=_param_sampling_frequency
                 * parent paramClass=_param_number_of_samples
                 * parent paramClass=_param_unit
                 * parent paramClass=_param_calibration_gain
                 * parent paramClass=_param_calibration_offset
                 * 
                 */
                public class channels_inner
                    extends OuterLoopClass.LoopClass
                {

                    final ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index index;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern get_channel_name_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type_name_pattern get_channel_type_name_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern get_channel_filename_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern get_channel_sampling_frequency_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern get_channel_nr_of_samples_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern get_calibration_gain_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern get_calibration_offset_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type get_channel_type = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename get_channel_filename = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency get_sampling_frequency = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples get_number_of_samples = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_unit get_unit = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset get_calibration_offset = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file get_channel_file = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0 = null;

                    channels_inner(TypeInt index) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1017)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1302)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1322)
                        this.index = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index(index);
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
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_name_pattern", get_channel_name_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_type_name_pattern", get_channel_type_name_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_filename_pattern", get_channel_filename_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_sampling_frequency_pattern", get_channel_sampling_frequency_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_nr_of_samples_pattern", get_channel_nr_of_samples_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("calibration_gain_pattern", get_calibration_gain_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("calibration_offset_pattern", get_calibration_offset_pattern());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("channel_name", get_channel_name());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
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
                        register("channel_filename", get_channel_filename());
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
                        register("number_of_samples", get_number_of_samples());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("unit", get_unit());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("calibration_gain", get_calibration_gain());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:521)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:240)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("calibration_offset", get_calibration_offset());
                        {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file obj = get_channel_file();
                            register("channel_file", obj);
                            obj.createParams();
                        }
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1407)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                            register("gen_id_0", obj);
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
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:935)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file obj = get_channel_file();
                            obj.createChannels();
                        }
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1407)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                            obj.createChannels();
                        }
                        registerChannel(get_gen_id_0());
                    }

                    public String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:548)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1045)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                        return "channels";
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index get_index() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:520)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1018)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:1025)
                        return this.index;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern get_channel_name_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_name_pattern == null) {
                            get_channel_name_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern();
                        }
                        return get_channel_name_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type_name_pattern get_channel_type_name_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_type_name_pattern == null) {
                            get_channel_type_name_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type_name_pattern();
                        }
                        return get_channel_type_name_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern get_channel_filename_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_filename_pattern == null) {
                            get_channel_filename_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern();
                        }
                        return get_channel_filename_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern get_channel_sampling_frequency_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_sampling_frequency_pattern == null) {
                            get_channel_sampling_frequency_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern();
                        }
                        return get_channel_sampling_frequency_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern get_channel_nr_of_samples_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_nr_of_samples_pattern == null) {
                            get_channel_nr_of_samples_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern();
                        }
                        return get_channel_nr_of_samples_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern get_calibration_gain_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_calibration_gain_pattern == null) {
                            get_calibration_gain_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern();
                        }
                        return get_calibration_gain_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern get_calibration_offset_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_calibration_offset_pattern == null) {
                            get_calibration_offset_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern();
                        }
                        return get_calibration_offset_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_name == null) {
                            get_channel_name = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name();
                        }
                        return get_channel_name;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type get_channel_type() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_type == null) {
                            get_channel_type = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_type();
                        }
                        return get_channel_type;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename get_channel_filename() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_filename == null) {
                            get_channel_filename = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename();
                        }
                        return get_channel_filename;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency get_sampling_frequency() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_sampling_frequency == null) {
                            get_sampling_frequency = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency();
                        }
                        return get_sampling_frequency;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples get_number_of_samples() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_number_of_samples == null) {
                            get_number_of_samples = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples();
                        }
                        return get_number_of_samples;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_unit get_unit() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_unit == null) {
                            get_unit = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_unit();
                        }
                        return get_unit;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_calibration_gain == null) {
                            get_calibration_gain = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain();
                        }
                        return get_calibration_gain;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset get_calibration_offset() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:480)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_calibration_offset == null) {
                            get_calibration_offset = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset();
                        }
                        return get_calibration_offset;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file get_channel_file() {
                        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:932)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_channel_file == null) {
                            get_channel_file = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file();
                        }
                        return get_channel_file;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0() {
                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1404)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                        if (get_gen_id_0 == null) {
                            get_gen_id_0 = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0();
                        }
                        return get_gen_id_0;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1399)
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1371)
                     * parent paramClass=_param_mapping
                     * 
                     */
                    public class Channel_gen_id_0
                        extends ChannelClass
                    {

                        private int channelNum = channelCounter ++;
                        ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get_mapping = null;

                        public void createParams() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1370)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1401)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:210)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Channel_gen_id_0.createParams()");
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
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1401)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Channel_gen_id_0.createChannels()");
                        }

                        public String id() {
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
                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1419)
                            return buffer();
                        }

                        public TypeString getSampleFormat() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1375)
                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1431)
                            // node.format.type=TypeString
                            TypeString value = get_sample_format().get();
                            return ((TypeString) value);
                        }

                        public TypeInt mapSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1376)
                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1444)
                            Type value = get_mapping().get();
                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                        }

                        public float getSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1377)
                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1459)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_channel_file().get();
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
                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1536)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_channel_file().get();
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
                            // jsignalml.JavaClassGen.applyLinearTransformationMethod(JavaClassGen.java:1620)
                            float calibGain = getCalibrationGain().getValue().floatValue();
                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                            return ((rawValue-calibOffs)*calibGain);
                        }

                        public double getSamplingFrequency() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1380)
                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1650)
                            Type value = get_sampling_frequency().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast.getValue();
                        }

                        public long getNumberOfSamples() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1381)
                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1663)
                            Type value = get_number_of_samples().get();
                            TypeInt cast = TypeInt.I.make(value);
                            return cast.safeLongValue();
                        }

                        public String getChannelName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1382)
                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1676)
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
                            // jsignalml.JavaClassGen.getChannelTypeMethod(JavaClassGen.java:1712)
                            Type value = get_channel_type().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public TypeFloat getCalibrationGain() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1384)
                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1728)
                            Type value = get_calibration_gain().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getCalibrationOffset() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1385)
                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1744)
                            Type value = get_calibration_offset().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get_mapping() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:518)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:993)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1003)
                            if (get_mapping == null) {
                                get_mapping = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping();
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

                            public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:454)
                                // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:751)
                                return this;
                            }

                            public TypeInt call(TypeInt sample_nr) {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:455)
                                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:784)
                                // node.type=TypeInt
                                // node.expr=(sample_nr * sample_size)
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return ((TypeInt) sample_nr.mul(get_sample_size().get()));
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

                            public long call_p(long sample_nr) {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:457)
                                // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:832)
                                // node.type=TypeInt
                                // node.expr=(sample_nr * sample_size)
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return (sample_nr*get_sample_size().get_p());
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
                    public class File_channel_file
                        extends Signalml.FileClass
                    {


                        public File_channel_file() {
                            File main = default_filename;
                            Integer endIndex = (main.getAbsolutePath().length()-main.getName().length());
                            String dirname = main.getAbsolutePath().substring(0, endIndex);
                            String filename = (dirname + get_channel_filename().get().value);
                            currentFilename = new File(filename);
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
                            log.debug("File_channel_file.createParams()");
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:858)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:929)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:233)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:222)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("File_channel_file.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:859)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_file";
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

                        public String id() {
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
                    public class _param_calibration_gain
                        extends Param<TypeFloat>
                    {

                        Double get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "calibration_gain";
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
                            // pattern=(calibration_gain_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_calibration_gain_pattern().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_calibration_gain_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "calibration_gain_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_calibration_gains: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
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
                    public class _param_calibration_offset
                        extends Param<TypeFloat>
                    {

                        Double get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "calibration_offset";
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
                            // pattern=(calibration_offset_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_calibration_offset_pattern().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_calibration_offset_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "calibration_offset_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_calibration_offsets: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
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
                    public class _param_channel_filename
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_filename";
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
                            // pattern=(channel_filename_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_channel_filename_pattern().get();
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
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_filename_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_filename_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_filenames: ([a-zA-Z0-9._-]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([a-zA-Z0-9._-]+)")));
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
                            // line=(-1)
                            // line.type=TypeInt
                            // pattern=(channel_name_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_channel_name_pattern().get();
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
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_name_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_name_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_names: ([a-zA-Z0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([A-Za-z0-9]+)")));
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
                    public class _param_channel_nr_of_samples_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_nr_of_samples_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_nrs_of_samples: ([0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9]+)")));
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
                    public class _param_channel_sampling_frequency_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_sampling_frequency_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_sampling_frequencies: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
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
                    public class _param_channel_type
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_type";
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
                            // pattern=(channel_type_name_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_channel_type_name_pattern().get();
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
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:449)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:514)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_type_name_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                            return "channel_type_name_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:452)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:729)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_types: ([a-zA-Z0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([A-Za-z0-9]+)")));
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
                            // pattern=(channel_nr_of_samples_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_channel_nr_of_samples_pattern().get();
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
                            // pattern=(channel_sampling_frequency_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = ((TypeInt) new TypeInt(-1));
                            TypeString pattern = get_channel_sampling_frequency_pattern().get();
                            TypeInt group = ((TypeInt) new TypeInt(2));
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
                            return ((TypeString) new TypeString("\u03bcV"));
                        }

                    }

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
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:311)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:481)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:529)
                return "number_of_channels";
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
                // pattern=("number_of_channels: ([0-9]+.[0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = ((TypeInt) new TypeInt(-1));
                TypeString pattern = new TypeString("number_of_channels: ([0-9]+.[0-9]*)");
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
                return ((TypeString) Builtins.str().call(new TypeString(">i4")));
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

}
