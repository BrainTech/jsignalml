import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;
import jsignalml.BitForm;
import jsignalml.Builtins;
import jsignalml.ContextDumper;
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
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:171)
 * 
 */
public class ALT
    extends Signalml
{

    final static Logger log = new Logger(ALT.class);
    ALT.File_main get_main = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
        log.debug("ALT.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            ALT.File_main obj = get_main();
            register("main", obj);
            obj.createParams();
        }
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
        log.debug("ALT.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            ALT.File_main obj = get_main();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:179)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
        return "ALT";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        BasicConfigurator.configure();
        ALT reader = new ALT();
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
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:181)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:353)
        return null;
    }

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:182)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:362)
        return null;
    }

    public String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:183)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:371)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:184)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:343)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:380)
    }

    public ALT.File_main get_main() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:821)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
        if (get_main == null) {
            get_main = new ALT.File_main();
        }
        return get_main;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:767)
     * parent paramClass=_param_number_of_channels
     * parent paramClass=_param_sample_unit
     * parent paramClass=_param_sample_size
     * parent paramClass=_param_sample_format
     * 
     */
    public class File_main
        extends Signalml.FileClass
    {

        ALT.File_main._param_number_of_channels get_number_of_channels = null;
        ALT.File_main._param_sample_unit get_sample_unit = null;
        ALT.File_main._param_sample_size get_sample_size = null;
        ALT.File_main._param_sample_format get_sample_format = null;
        ALT.File_main.ChannelSet_data get_data = null;

        public File_main() {
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:803)
            return super.access(name);
        }

        public void register(String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:811)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            log.debug("File_main.createParams()");
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            register("number_of_channels", get_number_of_channels());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            register("sample_unit", get_sample_unit());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            register("sample_size", get_sample_size());
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            register("sample_format", get_sample_format());
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1248)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                ALT.File_main.ChannelSet_data obj = get_data();
                register("data", obj);
                obj.createParams();
            }
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
            log.debug("File_main.createChannels()");
            {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1248)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                ALT.File_main.ChannelSet_data obj = get_data();
                obj.createChannels();
            }
            registerChannelSet(get_data());
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:753)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
            return "main";
        }

        public ALT.File_main._param_number_of_channels get_number_of_channels() {
            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_number_of_channels == null) {
                get_number_of_channels = new ALT.File_main._param_number_of_channels();
            }
            return get_number_of_channels;
        }

        public ALT.File_main._param_sample_unit get_sample_unit() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_sample_unit == null) {
                get_sample_unit = new ALT.File_main._param_sample_unit();
            }
            return get_sample_unit;
        }

        public ALT.File_main._param_sample_size get_sample_size() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_sample_size == null) {
                get_sample_size = new ALT.File_main._param_sample_size();
            }
            return get_sample_size;
        }

        public ALT.File_main._param_sample_format get_sample_format() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_sample_format == null) {
                get_sample_format = new ALT.File_main._param_sample_format();
            }
            return get_sample_format;
        }

        public ALT.File_main.ChannelSet_data get_data() {
            // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
            // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1245)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
            if (get_data == null) {
                get_data = new ALT.File_main.ChannelSet_data();
            }
            return get_data;
        }


        /**
         * 
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
         * jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1240)
         * jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1226)
         * 
         */
        public class ChannelSet_data
            extends ChannelSetClass
        {

            ALT.File_main.ChannelSet_data.Loop_channels get_channels = null;

            public void createParams() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1242)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                log.debug("ChannelSet_data.createParams()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                    ALT.File_main.ChannelSet_data.Loop_channels obj = get_channels();
                    register("channels", obj);
                    obj.createParams();
                }
            }

            public void createChannels() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1225)
                // jsignalml.JavaClassGen.channelSetClass(JavaClassGen.java:1242)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                log.debug("ChannelSet_data.createChannels()");
                {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:956)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                    ALT.File_main.ChannelSet_data.Loop_channels obj = get_channels();
                    obj.createChannels();
                    obj.createLoopChannels();
                }
            }

            public String id() {
                // jsignalml.ASTNode$ChannelSet._accept(ASTNode.java:140)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1227)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "data";
            }

            public ALT.File_main.ChannelSet_data.Loop_channels get_channels() {
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:952)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                if (get_channels == null) {
                    get_channels = new ALT.File_main.ChannelSet_data.Loop_channels();
                }
                return get_channels;
                // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:953)
            }


            /**
             * 
             * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:928)
             * 
             */
            public class Loop_channels
                extends OuterLoopClass
            {


                public void createParams() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                    log.debug("Loop_channels.createParams()");
                }

                public void createChannels() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:927)
                    // jsignalml.JavaClassGen.outerLoopClass(JavaClassGen.java:949)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                    // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                    log.debug("Loop_channels.createChannels()");
                }

                public String id() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:929)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                    return "channels";
                }

                protected TypeList getSequence() {
                    // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:930)
                    // jsignalml.JavaClassGen.sequenceMethod(JavaClassGen.java:965)
                    TypeList range = ((TypeList) Builtins.range().call(get_number_of_channels().get()));
                    return range;
                }

                protected ALT.File_main.ChannelSet_data.Loop_channels.channels_inner createLoop(Type index) {
                    // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                    // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1193)
                    // jsignalml.JavaClassGen.createLoopMethod(JavaClassGen.java:982)
                    return new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner(((TypeInt) index));
                }


                /**
                 * 
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                 * jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1001)
                 * jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:933)
                 * parent paramClass=_param_channel_name_pattern
                 * parent paramClass=_param_channel_filename_pattern
                 * parent paramClass=_param_channel_sampling_frequency_pattern
                 * parent paramClass=_param_channel_nr_of_samples_pattern
                 * parent paramClass=_param_calibration_gain_pattern
                 * parent paramClass=_param_calibration_offset_pattern
                 * parent paramClass=_param_channel_name
                 * parent paramClass=_param_channel_filename
                 * parent paramClass=_param_sampling_frequency
                 * parent paramClass=_param_number_of_samples
                 * parent paramClass=_param_calibration_gain
                 * parent paramClass=_param_calibration_offset
                 * 
                 */
                public class channels_inner
                    extends OuterLoopClass.LoopClass
                {

                    final ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index index;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern get_channel_name_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern get_channel_filename_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern get_channel_sampling_frequency_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern get_channel_nr_of_samples_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern get_calibration_gain_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern get_calibration_offset_pattern = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name get_channel_name = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename get_channel_filename = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency get_sampling_frequency = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples get_number_of_samples = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset get_calibration_offset = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file get_channel_file = null;
                    ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0 = null;

                    channels_inner(TypeInt index) {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                        // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1191)
                        // jsignalml.JavaClassGen.loopClassConstructor(JavaClassGen.java:1211)
                        this.index = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index(index);
                    }

                    public void createParams() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        log.debug("channels_inner.createParams()");
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:918)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("index", this.index);
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_name_pattern", get_channel_name_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_filename_pattern", get_channel_filename_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_sampling_frequency_pattern", get_channel_sampling_frequency_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_nr_of_samples_pattern", get_channel_nr_of_samples_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("calibration_gain_pattern", get_calibration_gain_pattern());
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("calibration_offset_pattern", get_calibration_offset_pattern());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_name", get_channel_name());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("channel_filename", get_channel_filename());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("sampling_frequency", get_sampling_frequency());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("number_of_samples", get_number_of_samples());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("calibration_gain", get_calibration_gain());
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        register("calibration_offset", get_calibration_offset());
                        {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file obj = get_channel_file();
                            register("channel_file", obj);
                            obj.createParams();
                        }
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1295)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                            register("gen_id_0", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:932)
                        // jsignalml.JavaClassGen.loopClass(JavaClassGen.java:1003)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                        // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                        log.debug("channels_inner.createChannels()");
                        {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:824)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file obj = get_channel_file();
                            obj.createChannels();
                        }
                        {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1295)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 obj = get_gen_id_0();
                            obj.createChannels();
                        }
                        registerChannel(get_gen_id_0());
                    }

                    public String id() {
                        // jsignalml.ASTNode$ForLoop._accept(ASTNode.java:522)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:934)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                        return "channels";
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.index get_index() {
                        // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:907)
                        // jsignalml.JavaClassGen.iternameGetter(JavaClassGen.java:914)
                        return this.index;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern get_channel_name_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_name_pattern == null) {
                            get_channel_name_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name_pattern();
                        }
                        return get_channel_name_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern get_channel_filename_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_filename_pattern == null) {
                            get_channel_filename_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename_pattern();
                        }
                        return get_channel_filename_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern get_channel_sampling_frequency_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_sampling_frequency_pattern == null) {
                            get_channel_sampling_frequency_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_sampling_frequency_pattern();
                        }
                        return get_channel_sampling_frequency_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern get_channel_nr_of_samples_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_nr_of_samples_pattern == null) {
                            get_channel_nr_of_samples_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_nr_of_samples_pattern();
                        }
                        return get_channel_nr_of_samples_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern get_calibration_gain_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_calibration_gain_pattern == null) {
                            get_calibration_gain_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain_pattern();
                        }
                        return get_calibration_gain_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern get_calibration_offset_pattern() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_calibration_offset_pattern == null) {
                            get_calibration_offset_pattern = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset_pattern();
                        }
                        return get_calibration_offset_pattern;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name get_channel_name() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_name == null) {
                            get_channel_name = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_name();
                        }
                        return get_channel_name;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename get_channel_filename() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_filename == null) {
                            get_channel_filename = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_channel_filename();
                        }
                        return get_channel_filename;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency get_sampling_frequency() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_sampling_frequency == null) {
                            get_sampling_frequency = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_sampling_frequency();
                        }
                        return get_sampling_frequency;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples get_number_of_samples() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_number_of_samples == null) {
                            get_number_of_samples = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_number_of_samples();
                        }
                        return get_number_of_samples;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain get_calibration_gain() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_calibration_gain == null) {
                            get_calibration_gain = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_gain();
                        }
                        return get_calibration_gain;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset get_calibration_offset() {
                        // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_calibration_offset == null) {
                            get_calibration_offset = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner._param_calibration_offset();
                        }
                        return get_calibration_offset;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file get_channel_file() {
                        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:821)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_channel_file == null) {
                            get_channel_file = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.File_channel_file();
                        }
                        return get_channel_file;
                    }

                    public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 get_gen_id_0() {
                        // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                        // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1292)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                        if (get_gen_id_0 == null) {
                            get_gen_id_0 = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0();
                        }
                        return get_gen_id_0;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                     * jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1287)
                     * jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1260)
                     * parent paramClass=_param_mapping
                     * 
                     */
                    public class Channel_gen_id_0
                        extends ChannelClass
                    {

                        ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get_mapping = null;

                        public void createParams() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            log.debug("Channel_gen_id_0.createParams()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:450)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            register("mapping", get_mapping());
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1259)
                            // jsignalml.JavaClassGen.channelClass(JavaClassGen.java:1289)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            log.debug("Channel_gen_id_0.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1262)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "gen_id_0";
                        }

                        protected MyBuffer _buffer() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1263)
                            // jsignalml.JavaClassGen.underBufferMethod(JavaClassGen.java:1304)
                            return buffer();
                        }

                        public TypeString getSampleFormat() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1264)
                            // jsignalml.JavaClassGen.sampleFormatMethod(JavaClassGen.java:1316)
                            // node.format.type=TypeString
                            TypeString value = get_sample_format().get();
                            return ((TypeString) value);
                        }

                        public TypeInt mapSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1265)
                            // jsignalml.JavaClassGen.mapSampleMethod(JavaClassGen.java:1329)
                            Type value = get_mapping().get();
                            return TypeInt.I.make(value.call(new TypeInt(sample)));
                        }

                        public float getSample(long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1266)
                            // jsignalml.JavaClassGen.getSampleMethod(JavaClassGen.java:1345)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_channel_file().get();
                            Signalml.FileClass fileHandler = ((Signalml.FileClass) dataFileId);
                            File file = fileHandler.getCurrentFilename();
                            if (file == null) {
                                fileHandler.open(null);
                            }
                            ByteBuffer buffer = fileHandler.buffer().source;
                            float calibGain = getCalibrationGain().getValue().floatValue();
                            float calibOffs = getCalibrationOffset().getValue().floatValue();
                            float sampleUnit = getSampleUnit().getValue().floatValue();
                            float value = format.read(buffer, ((int) get_mapping().call_p(sample)));
                            return (((value-calibOffs)*calibGain)*sampleUnit);
                        }

                        public void getSamples(FloatBuffer dst, long sample) {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1267)
                            // jsignalml.JavaClassGen.getSamplesMethod(JavaClassGen.java:1418)
                            TypeString format_ = this.getSampleFormat();
                            BitForm format = BitForm.get(format_);
                            Type dataFileId = get_channel_file().get();
                            Signalml.FileClass fileHandler = ((Signalml.FileClass) dataFileId);
                            File file = fileHandler.getCurrentFilename();
                            if (file == null) {
                                fileHandler.open(null);
                            }
                            ByteBuffer buffer = fileHandler.buffer().source;
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
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1268)
                            // jsignalml.JavaClassGen.getSamplingFrequencyMethod(JavaClassGen.java:1496)
                            Type value = get_sampling_frequency().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast.getValue();
                        }

                        public long getNumberOfSamples() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1269)
                            // jsignalml.JavaClassGen.getNumberOfSamplesMethod(JavaClassGen.java:1509)
                            Type value = get_number_of_samples().get();
                            TypeInt cast = TypeInt.I.make(value);
                            return cast.safeLongValue();
                        }

                        public String getChannelName() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1270)
                            // jsignalml.JavaClassGen.getChannelNameMethod(JavaClassGen.java:1535)
                            Type value = get_channel_name().get();
                            TypeString stringValue = ((TypeString) value);
                            return stringValue.getValue();
                        }

                        public TypeFloat getCalibrationGain() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1271)
                            // jsignalml.JavaClassGen.getCalibrationGainMethod(JavaClassGen.java:1550)
                            Type value = get_calibration_gain().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getCalibrationOffset() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1272)
                            // jsignalml.JavaClassGen.getCalibrationOffsetMethod(JavaClassGen.java:1571)
                            Type value = get_calibration_offset().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public TypeFloat getSampleUnit() {
                            // jsignalml.ASTNode$Channel._accept(ASTNode.java:188)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1273)
                            // jsignalml.JavaClassGen.getSampleUnitMethod(JavaClassGen.java:1523)
                            Type value = get_sample_unit().get();
                            TypeFloat cast = TypeFloat.I.make(value);
                            return cast;
                        }

                        public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get_mapping() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:447)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:882)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:892)
                            if (get_mapping == null) {
                                get_mapping = new ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping();
                            }
                            return get_mapping;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_mapping
                            extends FunctionParam<TypeInt>
                        {


                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                                return "mapping";
                            }

                            public ALT.File_main.ChannelSet_data.Loop_channels.channels_inner.Channel_gen_id_0 ._param_mapping get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:395)
                                // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:651)
                                return this;
                            }

                            public TypeInt call(TypeInt sample_nr) {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:678)
                                // node.type=TypeInt
                                // node.expr=(sample_nr * sample_size)
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return ((TypeInt) sample_nr.mul(get_sample_size().get()));
                            }

                            public TypeInt call(List<Type> args) {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
                                // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:688)
                                if (args.size()!= 1) {
                                    throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
                                }
                                return this.call(((TypeInt) args.get(0)));
                            }

                            public long call_p(long sample_nr) {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:398)
                                // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:726)
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
                     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:767)
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
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:803)
                            return super.access(name);
                        }

                        public void register(String name, Context child) {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:811)
                            super.register(name, child);
                        }

                        public void createParams() {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            log.debug("File_channel_file.createParams()");
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:752)
                            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:818)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                            // jsignalml.JavaClassGen.access$12(JavaClassGen.java:1670)
                            log.debug("File_channel_file.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:431)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:753)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_file";
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeFloat
                     * --> nodetype=TypeFloat
                     * 
                     */
                    public class _param_calibration_gain
                        extends Param<TypeFloat>
                    {

                        Double get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "calibration_gain";
                        }

                        protected TypeFloat _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=TypeFloat
                            // node._read_type=TypeFloat
                            // --> nodetype=TypeFloat
                            // format=("<f4")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(calibration_gain_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_calibration_gain_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeFloat _t = null;
                            TypeFloat value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Double get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:572)
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_calibration_gain_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "calibration_gain_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_calibration_gains: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=unknown
                     * --> nodetype=TypeFloat
                     * 
                     */
                    public class _param_calibration_offset
                        extends Param<TypeFloat>
                    {

                        Double get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "calibration_offset";
                        }

                        protected TypeFloat _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=unknown
                            // node._read_type=TypeFloat
                            // --> nodetype=TypeFloat
                            // format=("<f4")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(calibration_offset_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_calibration_offset_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeFloat _t = null;
                            TypeFloat value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Double get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:572)
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
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_calibration_offset_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "calibration_offset_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_calibration_offsets: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_filename
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_filename";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=TypeString
                            // node._read_type=TypeString
                            // --> nodetype=TypeString
                            // format=("|S2")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(channel_filename_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_channel_filename_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeString _t = null;
                            TypeString value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_filename_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_filename_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_filenames: ([a-zA-Z0-9._-]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([a-zA-Z0-9._-]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_name
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_name";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=TypeString
                            // node._read_type=TypeString
                            // --> nodetype=TypeString
                            // format=("|S1")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(channel_name_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_channel_name_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeString _t = null;
                            TypeString value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_name_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_name_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_names: ([a-zA-Z0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([A-Za-z0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_nr_of_samples_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_nr_of_samples_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_nrs_of_samples: ([0-9]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeString
                     * --> nodetype=TypeString
                     * 
                     */
                    public class _param_channel_sampling_frequency_pattern
                        extends Param<TypeString>
                    {


                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "channel_sampling_frequency_pattern";
                        }

                        protected TypeString _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                            // node.type=TypeString
                            // node.expr.type=TypeString
                            // --> nodetype=TypeString
                            return ((TypeString) new TypeString("channel_sampling_frequencies: ([0-9.eE]+,){").add(Builtins.str().call(get_index().get())).add(new TypeString("}([0-9.eE]+)")));
                        }

                    }


                    /**
                     * 
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_number_of_samples
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "number_of_samples";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=TypeInt
                            // node._read_type=TypeInt
                            // --> nodetype=TypeInt
                            // format=("|u1")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(channel_nr_of_samples_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_channel_nr_of_samples_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeInt _t = null;
                            TypeInt value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:572)
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
                     * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
                     * node.type=TypeFloat
                     * --> nodetype=TypeFloat
                     * 
                     */
                    public class _param_sampling_frequency
                        extends Param<TypeFloat>
                    {

                        Double get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "sampling_frequency";
                        }

                        protected TypeFloat _get() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                            // node.type=TypeFloat
                            // node._read_type=TypeInt
                            // --> nodetype=TypeFloat
                            // format=("<u2")
                            // format.type=TypeString
                            // line=(- 1)
                            // line.type=TypeInt
                            // pattern=(channel_sampling_frequency_pattern)
                            // pattern.type=TypeString
                            // group=(2)
                            // group.type=TypeInt
                            TypeInt line = new TypeInt(1).neg();
                            TypeString pattern = get_channel_sampling_frequency_pattern().get();
                            TypeInt group = new TypeInt(2);
                            TextBuffer textBuf = textBuffer();
                            TypeFloat _t = null;
                            TypeFloat value = textBuf.read(line, pattern, group, _t);
                            return value;
                        }

                        public Double get_p() {
                            // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                            // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:572)
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
                     * jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
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
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1186)
                            this.cache = index;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1189)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                            return "index";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:906)
                            // jsignalml.JavaClassGen.indexClass(JavaClassGen.java:1196)
                            throw new RuntimeException();
                        }

                        public Long get_p() {
                            // jsignalml.ASTNode$Itername._accept(ASTNode.java:494)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
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
         * jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:421)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_number_of_channels
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:422)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "number_of_channels";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:542)
                // node.type=TypeInt
                // node._read_type=TypeInt
                // --> nodetype=TypeInt
                // format=("|u1")
                // format.type=TypeString
                // line=(- 1)
                // line.type=TypeInt
                // pattern=("number_of_channels: ([0-9]+.[0-9]*)")
                // pattern.type=TypeString
                // group=(1)
                // group.type=TypeInt
                TypeInt line = new TypeInt(1).neg();
                TypeString pattern = new TypeString("number_of_channels: ([0-9]+.[0-9]*)");
                TypeInt group = new TypeInt(1);
                TextBuffer textBuf = textBuffer();
                TypeInt _t = null;
                TypeInt value = textBuf.read(line, pattern, group, _t);
                return value;
            }

            public Long get_p() {
                // jsignalml.ASTNode$TextParam._accept(ASTNode.java:288)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:423)
                // jsignalml.JavaClassGen.readParamFunction(JavaClassGen.java:572)
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
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeString
         * --> nodetype=TypeString
         * 
         */
        public class _param_sample_format
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "sample_format";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=TypeString
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) Builtins.str().call(new TypeString(">i4")));
            }

        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:443)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_sample_size
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "sample_size";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(4));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
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
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
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

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:458)
                return "sample_unit";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:629)
                // node.type=TypeFloat
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
            }

            public Long get_p() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1)
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

}
