import java.io.File;
import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeString;
import jsignalml.codec.Context;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.logging.Logger;
import org.apache.log4j.BasicConfigurator;


/**
 * 
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
 * parent paramClass=_param_a
 * parent paramClass=_param_description
 * 
 */
public class TwoFile
    extends Signalml
{

    final static Logger log = new Logger(TwoFile.class);
    private int channelCounter = 0;
    TwoFile.File_g get_g = null;
    TwoFile.File_b get_b = null;
    TwoFile._param_a get_a = null;
    TwoFile._param_description get_description = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("TwoFile.createParams()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1232)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            TwoFile.File_g obj = get_g();
            register("g", obj);
            obj.createParams();
        }
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1232)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            TwoFile.File_b obj = get_b();
            register("b", obj);
            obj.createParams();
        }
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("a", get_a());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("description", get_description());
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("TwoFile.createChannels()");
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1232)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            TwoFile.File_g obj = get_g();
            obj.createChannels();
        }
        {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1232)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            TwoFile.File_b obj = get_b();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:193)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
        return "TwoFile";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:194)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:313)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tTwoFile inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        TwoFile reader = new TwoFile();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        int nrOfChannelSets = reader.getNumberOfChannelSets();
        for (int k = 0; (k<nrOfChannelSets); k ++) {
            ChannelSet channelSet = reader.get_set(k);
            int nrOfChannels = channelSet.getNumberOfChannels();
            int nrOfChannelsToShow = nrOfChannels;
            System.out.println(("Input file for TwoFile codec: "+ args[ 0 ]));
            System.out.println((("Input file has "+ nrOfChannels)+" channels"));
            if (argc > 1) {
                nrOfChannelsToShow = argc;
            }
            for (int j = 1; (j<= nrOfChannelsToShow); j ++) {
                int channelNr = (j- 1);
                if (argc > 1) {
                    channelNr = Integer.decode(args[j]).intValue();
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
    }

    public File getCurrentFilename() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:195)
        // jsignalml.JavaClassGen.getCurrentFilenameMethod(JavaClassGen.java:437)
        return null;
    }

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:196)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:446)
        return null;
    }

    public String getFormatID() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:197)
        // jsignalml.JavaClassGen.getFormatIDMethod(JavaClassGen.java:455)
        return null;
    }

    public void open(File filename) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:198)
        // jsignalml.JavaClassGen.codecOpenMethod(JavaClassGen.java:427)
        this.default_filename = filename;
    }

    public void close() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:199)
        // jsignalml.JavaClassGen.closeMethod(JavaClassGen.java:539)
    }

    public String getFormatName() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:200)
        // jsignalml.JavaClassGen.getFormatNameMethod(JavaClassGen.java:464)
        get_header();
        String formatName = get_header().get_format_id().name.get().toString();
        return formatName;
    }

    public String getFormatProvider() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:201)
        // jsignalml.JavaClassGen.getFormatProviderMethod(JavaClassGen.java:479)
        get_header();
        String formatProvider = get_header().get_format_id().provider.get().toString();
        return formatProvider;
    }

    public String getFormatVersion() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:202)
        // jsignalml.JavaClassGen.getFormatVersionMethod(JavaClassGen.java:494)
        get_header();
        String formatVersion = get_header().get_format_id().version.get().toString();
        return formatVersion;
    }

    public String getCodecProvider() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:203)
        // jsignalml.JavaClassGen.getCodecProviderMethod(JavaClassGen.java:509)
        get_header();
        String codecProvider = get_header().get_codec_id().provider.get().toString();
        return codecProvider;
    }

    public String getCodecVersion() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:204)
        // jsignalml.JavaClassGen.getCodecVersionMethod(JavaClassGen.java:524)
        get_header();
        String codecVersion = get_header().get_codec_id().version.get().toString();
        return codecVersion;
    }

    public TwoFile.File_g get_g() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1229)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_g == null) {
            get_g = new TwoFile.File_g();
        }
        return get_g;
    }

    public TwoFile.File_b get_b() {
        // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
        // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1229)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_b == null) {
            get_b = new TwoFile.File_b();
        }
        return get_b;
    }

    public TwoFile._param_a get_a() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_a == null) {
            get_a = new TwoFile._param_a();
        }
        return get_a;
    }

    public TwoFile._param_description get_description() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_description == null) {
            get_description = new TwoFile._param_description();
        }
        return get_description;
    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1170)
     * parent paramClass=_param_description
     * 
     */
    public class File_b
        extends Signalml.FileClass
    {

        TwoFile.File_b._param_description get_description = null;

        public File_b() {
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1211)
            return super.access(name);
        }

        public void register(String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1219)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_b.createParams()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("description", get_description());
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_b.createChannels()");
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1156)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "b";
        }

        public TwoFile.File_b._param_description get_description() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_description == null) {
                get_description = new TwoFile.File_b._param_description();
            }
            return get_description;
        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_description
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "description";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                // node.type=unknown
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("booooooo"));
            }

        }

    }


    /**
     * 
     * jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
     * jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1170)
     * parent paramClass=_param_description
     * 
     */
    public class File_g
        extends Signalml.FileClass
    {

        TwoFile.File_g._param_description get_description = null;

        public File_g() {
        }

        public Type access(String name) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1211)
            return super.access(name);
        }

        public void register(String name, Context child) {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1219)
            super.register(name, child);
        }

        public void createParams() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_g.createParams()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("description", get_description());
        }

        public void createChannels() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1155)
            // jsignalml.JavaClassGen.fileClass(JavaClassGen.java:1226)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("File_g.createChannels()");
        }

        public String id() {
            // jsignalml.ASTNode$FileHandle._accept(ASTNode.java:457)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1156)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "g";
        }

        public TwoFile.File_g._param_description get_description() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_description == null) {
                get_description = new TwoFile.File_g._param_description();
            }
            return get_description;
        }


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
         * node.type=unknown
         * --> nodetype=TypeString
         * 
         */
        public class _param_description
            extends Param<TypeString>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "description";
            }

            protected TypeString _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                // node.type=unknown
                // node.expr.type=TypeString
                // --> nodetype=TypeString
                return ((TypeString) new TypeString("gooooooo"));
            }

        }

    }


    /**
     * 
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=unknown
     * --> nodetype=TypeInt
     * 
     */
    public class _param_a
        extends Param<TypeInt>
    {

        Long get_p = null;

        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "a";
        }

        protected TypeInt _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeInt
            // --> nodetype=TypeInt
            return ((TypeInt) get_description().get());
        }

        public Long get_p() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:1001)
            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:1039)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
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
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=unknown
     * --> nodetype=TypeInt
     * 
     */
    public class _param_description
        extends Param<TypeInt>
    {

        Long get_p = null;

        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "description";
        }

        protected TypeInt _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeInt
            // --> nodetype=TypeInt
            return ((TypeInt) new TypeInt(123456));
        }

        public Long get_p() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:1001)
            // jsignalml.JavaClassGen.getMethod_p(JavaClassGen.java:1039)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_p == null) {
                get_p = this.get().safeLongValue();
            }
            return get_p;
        }

    }

}
