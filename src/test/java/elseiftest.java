import java.io.File;
import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.Header;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.logging.Logger;
import org.apache.log4j.BasicConfigurator;


/**
 * 
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:185)
 * parent paramClass=_param_p1
 * parent paramClass=_param_p2
 * 
 */
public class elseiftest
    extends Signalml
{

    final static Logger log = new Logger(elseiftest.class);
    private int channelCounter = 0;
    elseiftest.header get_header = null;
    elseiftest._param_p1 get_p1 = null;
    elseiftest.If_if1 get_if1 = null;
    elseiftest._param_p2 get_p2 = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("elseiftest.createParams()");
        {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1144)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            elseiftest.header obj = get_header();
            register("header", obj);
            obj.createParams();
        }
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("p1", get_p1());
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            elseiftest.If_if1 obj = get_if1();
            register("if1", obj);
            obj.createParams();
        }
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("p2", get_p2());
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("elseiftest.createChannels()");
        {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1144)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            elseiftest.header obj = get_header();
            obj.createChannels();
        }
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            elseiftest.If_if1 obj = get_if1();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:193)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
        return "elseiftest";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:194)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:313)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\telseiftest inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        elseiftest reader = new elseiftest();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        int nrOfChannelSets = reader.getNumberOfChannelSets();
        for (int k = 0; (k<nrOfChannelSets); k ++) {
            ChannelSet channelSet = reader.get_set(k);
            int nrOfChannels = channelSet.getNumberOfChannels();
            int nrOfChannelsToShow = nrOfChannels;
            System.out.println(("Input file for elseiftest codec: "+ args[ 0 ]));
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

    public elseiftest.header get_header() {
        // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
        // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1141)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_header == null) {
            get_header = new elseiftest.header();
        }
        return get_header;
    }

    public elseiftest._param_p1 get_p1() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_p1 == null) {
            get_p1 = new elseiftest._param_p1();
        }
        return get_p1;
    }

    public elseiftest.If_if1 get_if1() {
        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_if1 == null) {
            get_if1 = new elseiftest.If_if1();
        }
        return get_if1;
    }

    public elseiftest._param_p2 get_p2() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_p2 == null) {
            get_p2 = new elseiftest._param_p2();
        }
        return get_p2;
    }


    /**
     * 
     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1446)
     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1431)
     * parent paramClass=_param_p1
     * 
     */
    public class If_if1
        extends ConditionalClass
    {

        elseiftest.If_if1 ._param_p1 get_p1 = null;
        elseiftest.If_if1 .ElseIf_gen_id_0 get_gen_id_0 = null;
        elseiftest.If_if1 .If_if11 get_if11 = null;

        public void createParamsIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createParamsIf()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            register("p1", get_p1());
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                elseiftest.If_if1 .If_if11 obj = get_if11();
                register("if11", obj);
                obj.createParams();
            }
        }

        public void createChannelsIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createChannelsIf()");
            {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                elseiftest.If_if1 .If_if11 obj = get_if11();
                obj.createChannels();
            }
        }

        public void createParamsElseIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createParamsElseIf()");
            {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                elseiftest.If_if1 .ElseIf_gen_id_0 obj = get_gen_id_0();
                register("gen_id_0", obj);
                obj.createParams();
            }
        }

        public void createChannelsElseIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createChannelsElseIf()");
            {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                elseiftest.If_if1 .ElseIf_gen_id_0 obj = get_gen_id_0();
                obj.createChannels();
            }
        }

        public void createParamsElse() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createParamsElse()");
        }

        public void createChannelsElse() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("If_if1.createChannelsElse()");
        }

        public boolean hasElseIf() {
            return true;
        }

        public String id() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1432)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "if1";
        }

        public Type getCondition() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1433)
            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1472)
            Type test = new TypeInt(1);
            return test;
        }

        public elseiftest.If_if1 ._param_p1 get_p1() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_p1 == null) {
                get_p1 = new elseiftest.If_if1 ._param_p1();
            }
            return get_p1;
        }

        public elseiftest.If_if1 .ElseIf_gen_id_0 get_gen_id_0() {
            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_gen_id_0 == null) {
                get_gen_id_0 = new elseiftest.If_if1 .ElseIf_gen_id_0();
            }
            return get_gen_id_0;
        }

        public elseiftest.If_if1 .If_if11 get_if11() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_if11 == null) {
                get_if11 = new elseiftest.If_if1 .If_if11();
            }
            return get_if11;
        }


        /**
         * 
         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
         * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
         * parent paramClass=_param_p1
         * 
         */
        public class ElseIf_gen_id_0
            extends ConditionalClass.ElseIfBranchClass
        {

            elseiftest.If_if1 .ElseIf_gen_id_0 ._param_p1 get_p1 = null;
            elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 get_gen_id_1 = null;

            public boolean hasElseIf() {
                return true;
            }

            public void createParamsIf() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                register("p1", get_p1());
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createChannelsIf()");
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 obj = get_gen_id_1();
                    register("gen_id_1", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 obj = get_gen_id_1();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("ElseIf_gen_id_0.createChannelsElse()");
            }

            public String id() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "gen_id_0";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                Type test = new TypeInt(0);
                return test;
            }

            public elseiftest.If_if1 .ElseIf_gen_id_0 ._param_p1 get_p1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_p1 == null) {
                    get_p1 = new elseiftest.If_if1 .ElseIf_gen_id_0 ._param_p1();
                }
                return get_p1;
            }

            public elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 get_gen_id_1() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_gen_id_1 == null) {
                    get_gen_id_1 = new elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1();
                }
                return get_gen_id_1;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
             * parent paramClass=_param_p1
             * 
             */
            public class ElseIf_gen_id_1
                extends ConditionalClass.ElseIfBranchClass
            {

                elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_p1 get_p1 = null;

                public boolean hasElseIf() {
                    return false;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    register("p1", get_p1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createParamsElseIf()");
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createChannelsElseIf()");
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createParamsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 obj = new elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2();
                        register("gen_id_2", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_1.createChannelsElse()");
                    {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 obj = new elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2();
                        obj.createChannels();
                    }
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                    return "gen_id_1";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                    Type test = new TypeInt(0);
                    return test;
                }

                public elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_p1 get_p1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                    if (get_p1 == null) {
                        get_p1 = new elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 ._param_p1();
                    }
                    return get_p1;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                 * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1503)
                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1486)
                 * parent paramClass=_param_p1
                 * 
                 */
                public class Else_gen_id_2
                    extends ConditionalClass.ElseBranchClass
                {

                    elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_p1 get_p1 = null;

                    public void createParams() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("Else_gen_id_2.createParams()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("p1", get_p1());
                    }

                    public void createChannels() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("Else_gen_id_2.createChannels()");
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "gen_id_2";
                    }

                    public elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_p1 get_p1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                        if (get_p1 == null) {
                            get_p1 = new elseiftest.If_if1 .ElseIf_gen_id_0 .ElseIf_gen_id_1 .Else_gen_id_2 ._param_p1();
                        }
                        return get_p1;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_p1
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "p1";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                            // node.type=TypeInt
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) new TypeInt(4));
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


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                 * node.type=TypeInt
                 * --> nodetype=TypeInt
                 * 
                 */
                public class _param_p1
                    extends Param<TypeInt>
                {

                    Long get_p = null;

                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "p1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(3));
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


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
             * node.type=TypeInt
             * --> nodetype=TypeInt
             * 
             */
            public class _param_p1
                extends Param<TypeInt>
            {

                Long get_p = null;

                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                    return "p1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                    // node.type=TypeInt
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(2));
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


        /**
         * 
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
         * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1446)
         * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1431)
         * parent paramClass=_param_p1
         * 
         */
        public class If_if11
            extends ConditionalClass
        {

            elseiftest.If_if1 .If_if11 ._param_p1 get_p1 = null;
            elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 get_gen_id_3 = null;
            elseiftest.If_if1 .If_if11 .If_if111 get_if111 = null;

            public void createParamsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createParamsIf()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                register("p1", get_p1());
                {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .If_if11 .If_if111 obj = get_if111();
                    register("if111", obj);
                    obj.createParams();
                }
            }

            public void createChannelsIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createChannelsIf()");
                {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .If_if11 .If_if111 obj = get_if111();
                    obj.createChannels();
                }
            }

            public void createParamsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createParamsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 obj = get_gen_id_3();
                    register("gen_id_3", obj);
                    obj.createParams();
                }
            }

            public void createChannelsElseIf() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createChannelsElseIf()");
                {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 obj = get_gen_id_3();
                    obj.createChannels();
                }
            }

            public void createParamsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createParamsElse()");
            }

            public void createChannelsElse() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("If_if11.createChannelsElse()");
            }

            public boolean hasElseIf() {
                return true;
            }

            public String id() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1432)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "if11";
            }

            public Type getCondition() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1433)
                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1472)
                Type test = new TypeInt(1);
                return test;
            }

            public elseiftest.If_if1 .If_if11 ._param_p1 get_p1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_p1 == null) {
                    get_p1 = new elseiftest.If_if1 .If_if11 ._param_p1();
                }
                return get_p1;
            }

            public elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 get_gen_id_3() {
                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_gen_id_3 == null) {
                    get_gen_id_3 = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3();
                }
                return get_gen_id_3;
            }

            public elseiftest.If_if1 .If_if11 .If_if111 get_if111() {
                // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_if111 == null) {
                    get_if111 = new elseiftest.If_if1 .If_if11 .If_if111();
                }
                return get_if111;
            }


            /**
             * 
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
             * parent paramClass=_param_p1
             * 
             */
            public class ElseIf_gen_id_3
                extends ConditionalClass.ElseIfBranchClass
            {

                elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 ._param_p1 get_p1 = null;
                elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 get_gen_id_4 = null;

                public boolean hasElseIf() {
                    return true;
                }

                public void createParamsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    register("p1", get_p1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 obj = get_gen_id_4();
                        register("gen_id_4", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 obj = get_gen_id_4();
                        obj.createChannels();
                    }
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createParamsElse()");
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("ElseIf_gen_id_3.createChannelsElse()");
                }

                public String id() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                    return "gen_id_3";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                    Type test = new TypeInt(0);
                    return test;
                }

                public elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 ._param_p1 get_p1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                    if (get_p1 == null) {
                        get_p1 = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 ._param_p1();
                    }
                    return get_p1;
                }

                public elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 get_gen_id_4() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                    if (get_gen_id_4 == null) {
                        get_gen_id_4 = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4();
                    }
                    return get_gen_id_4;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                 * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                 * parent paramClass=_param_p1
                 * 
                 */
                public class ElseIf_gen_id_4
                    extends ConditionalClass.ElseIfBranchClass
                {

                    elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_p1 get_p1 = null;

                    public boolean hasElseIf() {
                        return false;
                    }

                    public void createParamsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsIf()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("p1", get_p1());
                    }

                    public void createChannelsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsIf()");
                    }

                    public void createParamsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsElseIf()");
                    }

                    public void createChannelsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsElseIf()");
                    }

                    public void createParamsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createParamsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 obj = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5();
                            register("gen_id_5", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannelsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_4.createChannelsElse()");
                        {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 obj = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5();
                            obj.createChannels();
                        }
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "gen_id_4";
                    }

                    public Type getCondition() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                        // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                        Type test = new TypeInt(0);
                        return test;
                    }

                    public elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_p1 get_p1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                        if (get_p1 == null) {
                            get_p1 = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 ._param_p1();
                        }
                        return get_p1;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                     * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1503)
                     * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1486)
                     * parent paramClass=_param_p1
                     * 
                     */
                    public class Else_gen_id_5
                        extends ConditionalClass.ElseBranchClass
                    {

                        elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_p1 get_p1 = null;

                        public void createParams() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_5.createParams()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("p1", get_p1());
                        }

                        public void createChannels() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                            // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("Else_gen_id_5.createChannels()");
                        }

                        public String id() {
                            // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "gen_id_5";
                        }

                        public elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_p1 get_p1() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                            if (get_p1 == null) {
                                get_p1 = new elseiftest.If_if1 .If_if11 .ElseIf_gen_id_3 .ElseIf_gen_id_4 .Else_gen_id_5 ._param_p1();
                            }
                            return get_p1;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_p1
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                return "p1";
                            }

                            protected TypeInt _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                // node.type=TypeInt
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return ((TypeInt) new TypeInt(4));
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


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_p1
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "p1";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                            // node.type=TypeInt
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) new TypeInt(3));
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


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                 * node.type=TypeInt
                 * --> nodetype=TypeInt
                 * 
                 */
                public class _param_p1
                    extends Param<TypeInt>
                {

                    Long get_p = null;

                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "p1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(2));
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


            /**
             * 
             * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
             * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1446)
             * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1431)
             * parent paramClass=_param_p1
             * 
             */
            public class If_if111
                extends ConditionalClass
            {

                elseiftest.If_if1 .If_if11 .If_if111 ._param_p1 get_p1 = null;
                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 get_gen_id_6 = null;

                public void createParamsIf() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createParamsIf()");
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    register("p1", get_p1());
                }

                public void createChannelsIf() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createChannelsIf()");
                }

                public void createParamsElseIf() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createParamsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 obj = get_gen_id_6();
                        register("gen_id_6", obj);
                        obj.createParams();
                    }
                }

                public void createChannelsElseIf() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createChannelsElseIf()");
                    {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 obj = get_gen_id_6();
                        obj.createChannels();
                    }
                }

                public void createParamsElse() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createParamsElse()");
                }

                public void createChannelsElse() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                    log.debug("If_if111.createChannelsElse()");
                }

                public boolean hasElseIf() {
                    return true;
                }

                public String id() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1432)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                    return "if111";
                }

                public Type getCondition() {
                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1433)
                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1472)
                    Type test = new TypeInt(0);
                    return test;
                }

                public elseiftest.If_if1 .If_if11 .If_if111 ._param_p1 get_p1() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                    if (get_p1 == null) {
                        get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 ._param_p1();
                    }
                    return get_p1;
                }

                public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 get_gen_id_6() {
                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                    if (get_gen_id_6 == null) {
                        get_gen_id_6 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6();
                    }
                    return get_gen_id_6;
                }


                /**
                 * 
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                 * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                 * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                 * parent paramClass=_param_p1
                 * 
                 */
                public class ElseIf_gen_id_6
                    extends ConditionalClass.ElseIfBranchClass
                {

                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 ._param_p1 get_p1 = null;
                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 get_gen_id_7 = null;
                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 get_if1111 = null;

                    public boolean hasElseIf() {
                        return true;
                    }

                    public void createParamsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createParamsIf()");
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        register("p1", get_p1());
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 obj = get_if1111();
                            register("if1111", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannelsIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createChannelsIf()");
                        {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 obj = get_if1111();
                            obj.createChannels();
                        }
                    }

                    public void createParamsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createParamsElseIf()");
                        {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 obj = get_gen_id_7();
                            register("gen_id_7", obj);
                            obj.createParams();
                        }
                    }

                    public void createChannelsElseIf() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createChannelsElseIf()");
                        {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 obj = get_gen_id_7();
                            obj.createChannels();
                        }
                    }

                    public void createParamsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createParamsElse()");
                    }

                    public void createChannelsElse() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                        log.debug("ElseIf_gen_id_6.createChannelsElse()");
                    }

                    public String id() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "gen_id_6";
                    }

                    public Type getCondition() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                        // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                        Type test = new TypeInt(1);
                        return test;
                    }

                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 ._param_p1 get_p1() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                        if (get_p1 == null) {
                            get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 ._param_p1();
                        }
                        return get_p1;
                    }

                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 get_gen_id_7() {
                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                        if (get_gen_id_7 == null) {
                            get_gen_id_7 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7();
                        }
                        return get_gen_id_7;
                    }

                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 get_if1111() {
                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                        if (get_if1111 == null) {
                            get_if1111 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111();
                        }
                        return get_if1111;
                    }


                    /**
                     * 
                     * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                     * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                     * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                     * parent paramClass=_param_p1
                     * 
                     */
                    public class ElseIf_gen_id_7
                        extends ConditionalClass.ElseIfBranchClass
                    {

                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 ._param_p1 get_p1 = null;

                        public boolean hasElseIf() {
                            return false;
                        }

                        public void createParamsIf() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createParamsIf()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("p1", get_p1());
                        }

                        public void createChannelsIf() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createChannelsIf()");
                        }

                        public void createParamsElseIf() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createParamsElseIf()");
                        }

                        public void createChannelsElseIf() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createChannelsElseIf()");
                        }

                        public void createParamsElse() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createParamsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8();
                                register("gen_id_8", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsElse() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("ElseIf_gen_id_7.createChannelsElse()");
                            {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8();
                                obj.createChannels();
                            }
                        }

                        public String id() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "gen_id_7";
                        }

                        public Type getCondition() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                            Type test = new TypeInt(0);
                            return test;
                        }

                        public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 ._param_p1 get_p1() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                            if (get_p1 == null) {
                                get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 ._param_p1();
                            }
                            return get_p1;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                         * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1503)
                         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1486)
                         * parent paramClass=_param_p1
                         * 
                         */
                        public class Else_gen_id_8
                            extends ConditionalClass.ElseBranchClass
                        {

                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8 ._param_p1 get_p1 = null;

                            public void createParams() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Else_gen_id_8.createParams()");
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                register("p1", get_p1());
                            }

                            public void createChannels() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("Else_gen_id_8.createChannels()");
                            }

                            public String id() {
                                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                return "gen_id_8";
                            }

                            public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8 ._param_p1 get_p1() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                if (get_p1 == null) {
                                    get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .ElseIf_gen_id_7 .Else_gen_id_8 ._param_p1();
                                }
                                return get_p1;
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                             * node.type=TypeInt
                             * --> nodetype=TypeInt
                             * 
                             */
                            public class _param_p1
                                extends Param<TypeInt>
                            {

                                Long get_p = null;

                                public String id() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                    return "p1";
                                }

                                protected TypeInt _get() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                    // node.type=TypeInt
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return ((TypeInt) new TypeInt(4));
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


                        /**
                         * 
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_p1
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                return "p1";
                            }

                            protected TypeInt _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                // node.type=TypeInt
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return ((TypeInt) new TypeInt(3));
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


                    /**
                     * 
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1446)
                     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1431)
                     * parent paramClass=_param_p1
                     * 
                     */
                    public class If_if1111
                        extends ConditionalClass
                    {

                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 ._param_p1 get_p1 = null;
                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 get_gen_id_9 = null;

                        public void createParamsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createParamsIf()");
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            register("p1", get_p1());
                        }

                        public void createChannelsIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createChannelsIf()");
                        }

                        public void createParamsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createParamsElseIf()");
                            {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 obj = get_gen_id_9();
                                register("gen_id_9", obj);
                                obj.createParams();
                            }
                        }

                        public void createChannelsElseIf() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createChannelsElseIf()");
                            {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 obj = get_gen_id_9();
                                obj.createChannels();
                            }
                        }

                        public void createParamsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createParamsElse()");
                        }

                        public void createChannelsElse() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                            log.debug("If_if1111.createChannelsElse()");
                        }

                        public boolean hasElseIf() {
                            return true;
                        }

                        public String id() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1432)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "if1111";
                        }

                        public Type getCondition() {
                            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1433)
                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1472)
                            Type test = new TypeInt(0);
                            return test;
                        }

                        public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 ._param_p1 get_p1() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                            if (get_p1 == null) {
                                get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 ._param_p1();
                            }
                            return get_p1;
                        }

                        public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 get_gen_id_9() {
                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                            if (get_gen_id_9 == null) {
                                get_gen_id_9 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9();
                            }
                            return get_gen_id_9;
                        }


                        /**
                         * 
                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                         * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                         * parent paramClass=_param_p1
                         * 
                         */
                        public class ElseIf_gen_id_9
                            extends ConditionalClass.ElseIfBranchClass
                        {

                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 ._param_p1 get_p1 = null;
                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 get_gen_id_10 = null;

                            public boolean hasElseIf() {
                                return true;
                            }

                            public void createParamsIf() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createParamsIf()");
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                register("p1", get_p1());
                            }

                            public void createChannelsIf() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createChannelsIf()");
                            }

                            public void createParamsElseIf() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createParamsElseIf()");
                                {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 obj = get_gen_id_10();
                                    register("gen_id_10", obj);
                                    obj.createParams();
                                }
                            }

                            public void createChannelsElseIf() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createChannelsElseIf()");
                                {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 obj = get_gen_id_10();
                                    obj.createChannels();
                                }
                            }

                            public void createParamsElse() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createParamsElse()");
                            }

                            public void createChannelsElse() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                log.debug("ElseIf_gen_id_9.createChannelsElse()");
                            }

                            public String id() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                return "gen_id_9";
                            }

                            public Type getCondition() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                                Type test = new TypeInt(0);
                                return test;
                            }

                            public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 ._param_p1 get_p1() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                if (get_p1 == null) {
                                    get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 ._param_p1();
                                }
                                return get_p1;
                            }

                            public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 get_gen_id_10() {
                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                if (get_gen_id_10 == null) {
                                    get_gen_id_10 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10();
                                }
                                return get_gen_id_10;
                            }


                            /**
                             * 
                             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                             * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                             * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                             * parent paramClass=_param_p1
                             * 
                             */
                            public class ElseIf_gen_id_10
                                extends ConditionalClass.ElseIfBranchClass
                            {

                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 ._param_p1 get_p1 = null;
                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 get_if11111 = null;

                                public boolean hasElseIf() {
                                    return false;
                                }

                                public void createParamsIf() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createParamsIf()");
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    register("p1", get_p1());
                                    {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 obj = get_if11111();
                                        register("if11111", obj);
                                        obj.createParams();
                                    }
                                }

                                public void createChannelsIf() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createChannelsIf()");
                                    {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
                                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 obj = get_if11111();
                                        obj.createChannels();
                                    }
                                }

                                public void createParamsElseIf() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createParamsElseIf()");
                                }

                                public void createChannelsElseIf() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createChannelsElseIf()");
                                }

                                public void createParamsElse() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createParamsElse()");
                                    {
                                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11();
                                        register("gen_id_11", obj);
                                        obj.createParams();
                                    }
                                }

                                public void createChannelsElse() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                    // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                    // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                    log.debug("ElseIf_gen_id_10.createChannelsElse()");
                                    {
                                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                        // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11();
                                        obj.createChannels();
                                    }
                                }

                                public String id() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                    return "gen_id_10";
                                }

                                public Type getCondition() {
                                    // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                                    // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                                    Type test = new TypeInt(1);
                                    return test;
                                }

                                public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 ._param_p1 get_p1() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                    if (get_p1 == null) {
                                        get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 ._param_p1();
                                    }
                                    return get_p1;
                                }

                                public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 get_if11111() {
                                    // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                    // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
                                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                    if (get_if11111 == null) {
                                        get_if11111 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111();
                                    }
                                    return get_if11111;
                                }


                                /**
                                 * 
                                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                 * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1503)
                                 * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1486)
                                 * parent paramClass=_param_p1
                                 * 
                                 */
                                public class Else_gen_id_11
                                    extends ConditionalClass.ElseBranchClass
                                {

                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11 ._param_p1 get_p1 = null;

                                    public void createParams() {
                                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("Else_gen_id_11.createParams()");
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("p1", get_p1());
                                    }

                                    public void createChannels() {
                                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                        // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("Else_gen_id_11.createChannels()");
                                    }

                                    public String id() {
                                        // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                        return "gen_id_11";
                                    }

                                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11 ._param_p1 get_p1() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                        if (get_p1 == null) {
                                            get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .Else_gen_id_11 ._param_p1();
                                        }
                                        return get_p1;
                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                     * node.type=TypeInt
                                     * --> nodetype=TypeInt
                                     * 
                                     */
                                    public class _param_p1
                                        extends Param<TypeInt>
                                    {

                                        Long get_p = null;

                                        public String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                            return "p1";
                                        }

                                        protected TypeInt _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                            // node.type=TypeInt
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return ((TypeInt) new TypeInt(4));
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


                                /**
                                 * 
                                 * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                 * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1446)
                                 * jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:1431)
                                 * parent paramClass=_param_p1
                                 * 
                                 */
                                public class If_if11111
                                    extends ConditionalClass
                                {

                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 ._param_p1 get_p1 = null;
                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 get_gen_id_12 = null;

                                    public void createParamsIf() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createParamsIf()");
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        register("p1", get_p1());
                                    }

                                    public void createChannelsIf() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createChannelsIf()");
                                    }

                                    public void createParamsElseIf() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createParamsElseIf()");
                                        {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 obj = get_gen_id_12();
                                            register("gen_id_12", obj);
                                            obj.createParams();
                                        }
                                    }

                                    public void createChannelsElseIf() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createChannelsElseIf()");
                                        {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 obj = get_gen_id_12();
                                            obj.createChannels();
                                        }
                                    }

                                    public void createParamsElse() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createParamsElse()");
                                    }

                                    public void createChannelsElse() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
                                        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1448)
                                        // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                        log.debug("If_if11111.createChannelsElse()");
                                    }

                                    public boolean hasElseIf() {
                                        return true;
                                    }

                                    public String id() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1432)
                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                        return "if11111";
                                    }

                                    public Type getCondition() {
                                        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1433)
                                        // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1472)
                                        Type test = new TypeInt(0);
                                        return test;
                                    }

                                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 ._param_p1 get_p1() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                        if (get_p1 == null) {
                                            get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 ._param_p1();
                                        }
                                        return get_p1;
                                    }

                                    public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 get_gen_id_12() {
                                        // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                        // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                                        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                        if (get_gen_id_12 == null) {
                                            get_gen_id_12 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12();
                                        }
                                        return get_gen_id_12;
                                    }


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                     * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                                     * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                                     * parent paramClass=_param_p1
                                     * 
                                     */
                                    public class ElseIf_gen_id_12
                                        extends ConditionalClass.ElseIfBranchClass
                                    {

                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 ._param_p1 get_p1 = null;
                                        elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 get_gen_id_13 = null;

                                        public boolean hasElseIf() {
                                            return true;
                                        }

                                        public void createParamsIf() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createParamsIf()");
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            register("p1", get_p1());
                                        }

                                        public void createChannelsIf() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createChannelsIf()");
                                        }

                                        public void createParamsElseIf() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createParamsElseIf()");
                                            {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 obj = get_gen_id_13();
                                                register("gen_id_13", obj);
                                                obj.createParams();
                                            }
                                        }

                                        public void createChannelsElseIf() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createChannelsElseIf()");
                                            {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1558)
                                                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 obj = get_gen_id_13();
                                                obj.createChannels();
                                            }
                                        }

                                        public void createParamsElse() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createParamsElse()");
                                        }

                                        public void createChannelsElse() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                            log.debug("ElseIf_gen_id_12.createChannelsElse()");
                                        }

                                        public String id() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                            return "gen_id_12";
                                        }

                                        public Type getCondition() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                                            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                                            Type test = new TypeInt(0);
                                            return test;
                                        }

                                        public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 ._param_p1 get_p1() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                            if (get_p1 == null) {
                                                get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 ._param_p1();
                                            }
                                            return get_p1;
                                        }

                                        public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 get_gen_id_13() {
                                            // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                            // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1554)
                                            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                            if (get_gen_id_13 == null) {
                                                get_gen_id_13 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13();
                                            }
                                            return get_gen_id_13;
                                        }


                                        /**
                                         * 
                                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                         * jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1546)
                                         * jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1531)
                                         * parent paramClass=_param_p1
                                         * 
                                         */
                                        public class ElseIf_gen_id_13
                                            extends ConditionalClass.ElseIfBranchClass
                                        {

                                            elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 ._param_p1 get_p1 = null;

                                            public boolean hasElseIf() {
                                                return false;
                                            }

                                            public void createParamsIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createParamsIf()");
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                register("p1", get_p1());
                                            }

                                            public void createChannelsIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:303)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createChannelsIf()");
                                            }

                                            public void createParamsElseIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createParamsElseIf()");
                                            }

                                            public void createChannelsElseIf() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:304)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createChannelsElseIf()");
                                            }

                                            public void createParamsElse() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createParamsElse()");
                                                {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14();
                                                    register("gen_id_14", obj);
                                                    obj.createParams();
                                                }
                                            }

                                            public void createChannelsElse() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1530)
                                                // jsignalml.JavaClassGen.elseIfBranchClass(JavaClassGen.java:1551)
                                                // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:305)
                                                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                log.debug("ElseIf_gen_id_13.createChannelsElse()");
                                                {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                                                    // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                    elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14 obj = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14();
                                                    obj.createChannels();
                                                }
                                            }

                                            public String id() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1532)
                                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                                return "gen_id_13";
                                            }

                                            public Type getCondition() {
                                                // jsignalml.ASTNode$ElseIfBranch._accept(ASTNode.java:642)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1533)
                                                // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1571)
                                                Type test = new TypeInt(0);
                                                return test;
                                            }

                                            public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 ._param_p1 get_p1() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                                if (get_p1 == null) {
                                                    get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 ._param_p1();
                                                }
                                                return get_p1;
                                            }


                                            /**
                                             * 
                                             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                             * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1503)
                                             * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:1486)
                                             * parent paramClass=_param_p1
                                             * 
                                             */
                                            public class Else_gen_id_14
                                                extends ConditionalClass.ElseBranchClass
                                            {

                                                elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14 ._param_p1 get_p1 = null;

                                                public void createParams() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                    log.debug("Else_gen_id_14.createParams()");
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
                                                    // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                    register("p1", get_p1());
                                                }

                                                public void createChannels() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                                                    // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                                                    // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
                                                    // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                                                    log.debug("Else_gen_id_14.createChannels()");
                                                }

                                                public String id() {
                                                    // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                                    return "gen_id_14";
                                                }

                                                public elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14 ._param_p1 get_p1() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                                    // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                                                    // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                                                    // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                                                    if (get_p1 == null) {
                                                        get_p1 = new elseiftest.If_if1 .If_if11 .If_if111 .ElseIf_gen_id_6 .If_if1111 .ElseIf_gen_id_9 .ElseIf_gen_id_10 .If_if11111 .ElseIf_gen_id_12 .ElseIf_gen_id_13 .Else_gen_id_14 ._param_p1();
                                                    }
                                                    return get_p1;
                                                }


                                                /**
                                                 * 
                                                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                                 * node.type=TypeInt
                                                 * --> nodetype=TypeInt
                                                 * 
                                                 */
                                                public class _param_p1
                                                    extends Param<TypeInt>
                                                {

                                                    Long get_p = null;

                                                    public String id() {
                                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                                        return "p1";
                                                    }

                                                    protected TypeInt _get() {
                                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                                        // node.type=TypeInt
                                                        // node.expr.type=TypeInt
                                                        // --> nodetype=TypeInt
                                                        return ((TypeInt) new TypeInt(4));
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


                                            /**
                                             * 
                                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                             * node.type=TypeInt
                                             * --> nodetype=TypeInt
                                             * 
                                             */
                                            public class _param_p1
                                                extends Param<TypeInt>
                                            {

                                                Long get_p = null;

                                                public String id() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                                    return "p1";
                                                }

                                                protected TypeInt _get() {
                                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                                    // node.type=TypeInt
                                                    // node.expr.type=TypeInt
                                                    // --> nodetype=TypeInt
                                                    return ((TypeInt) new TypeInt(3));
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


                                        /**
                                         * 
                                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                         * node.type=TypeInt
                                         * --> nodetype=TypeInt
                                         * 
                                         */
                                        public class _param_p1
                                            extends Param<TypeInt>
                                        {

                                            Long get_p = null;

                                            public String id() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                                return "p1";
                                            }

                                            protected TypeInt _get() {
                                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                                // node.type=TypeInt
                                                // node.expr.type=TypeInt
                                                // --> nodetype=TypeInt
                                                return ((TypeInt) new TypeInt(2));
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


                                    /**
                                     * 
                                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                     * node.type=TypeInt
                                     * --> nodetype=TypeInt
                                     * 
                                     */
                                    public class _param_p1
                                        extends Param<TypeInt>
                                    {

                                        Long get_p = null;

                                        public String id() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                            return "p1";
                                        }

                                        protected TypeInt _get() {
                                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                            // node.type=TypeInt
                                            // node.expr.type=TypeInt
                                            // --> nodetype=TypeInt
                                            return ((TypeInt) new TypeInt(1));
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


                                /**
                                 * 
                                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                                 * node.type=TypeInt
                                 * --> nodetype=TypeInt
                                 * 
                                 */
                                public class _param_p1
                                    extends Param<TypeInt>
                                {

                                    Long get_p = null;

                                    public String id() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                        return "p1";
                                    }

                                    protected TypeInt _get() {
                                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                        // node.type=TypeInt
                                        // node.expr.type=TypeInt
                                        // --> nodetype=TypeInt
                                        return ((TypeInt) new TypeInt(3));
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


                            /**
                             * 
                             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                             * node.type=TypeInt
                             * --> nodetype=TypeInt
                             * 
                             */
                            public class _param_p1
                                extends Param<TypeInt>
                            {

                                Long get_p = null;

                                public String id() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                    return "p1";
                                }

                                protected TypeInt _get() {
                                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                    // node.type=TypeInt
                                    // node.expr.type=TypeInt
                                    // --> nodetype=TypeInt
                                    return ((TypeInt) new TypeInt(2));
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


                        /**
                         * 
                         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                         * node.type=TypeInt
                         * --> nodetype=TypeInt
                         * 
                         */
                        public class _param_p1
                            extends Param<TypeInt>
                        {

                            Long get_p = null;

                            public String id() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                                return "p1";
                            }

                            protected TypeInt _get() {
                                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                                // node.type=TypeInt
                                // node.expr.type=TypeInt
                                // --> nodetype=TypeInt
                                return ((TypeInt) new TypeInt(1));
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


                    /**
                     * 
                     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                     * node.type=TypeInt
                     * --> nodetype=TypeInt
                     * 
                     */
                    public class _param_p1
                        extends Param<TypeInt>
                    {

                        Long get_p = null;

                        public String id() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                            return "p1";
                        }

                        protected TypeInt _get() {
                            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                            // node.type=TypeInt
                            // node.expr.type=TypeInt
                            // --> nodetype=TypeInt
                            return ((TypeInt) new TypeInt(2));
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


                /**
                 * 
                 * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                 * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                 * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
                 * node.type=TypeInt
                 * --> nodetype=TypeInt
                 * 
                 */
                public class _param_p1
                    extends Param<TypeInt>
                {

                    Long get_p = null;

                    public String id() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                        return "p1";
                    }

                    protected TypeInt _get() {
                        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                        // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                        // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                        // node.type=TypeInt
                        // node.expr.type=TypeInt
                        // --> nodetype=TypeInt
                        return ((TypeInt) new TypeInt(1));
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


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
             * node.type=TypeInt
             * --> nodetype=TypeInt
             * 
             */
            public class _param_p1
                extends Param<TypeInt>
            {

                Long get_p = null;

                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                    return "p1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                    // node.type=TypeInt
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(1));
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


        /**
         * 
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         * 
         */
        public class _param_p1
            extends Param<TypeInt>
        {

            Long get_p = null;

            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "p1";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(1));
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


    /**
     * 
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=TypeInt
     * --> nodetype=TypeInt
     * 
     */
    public class _param_p1
        extends Param<TypeInt>
    {

        Long get_p = null;

        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "p1";
        }

        protected TypeInt _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=TypeInt
            // node.expr.type=TypeInt
            // --> nodetype=TypeInt
            return ((TypeInt) new TypeInt(1));
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
    public class _param_p2
        extends Param<TypeInt>
    {

        Long get_p = null;

        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "p2";
        }

        protected TypeInt _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeInt
            // --> nodetype=TypeInt
            return ((TypeInt) get_p1().get().mul(new TypeInt(100000)).add(get_if1().get().access("p1").mul(new TypeInt(10000))).add(get_if1().get().access("if11").access("p1").mul(new TypeInt(1000))).add(get_if1().get().access("if11").access("if111").access("p1").mul(new TypeInt(100))).add(get_if1().get().access("if11").access("if111").access("if1111").access("p1").mul(new TypeInt(10))).add(get_if1().get().access("if11").access("if111").access("if1111").access("if11111").access("p1").mul(new TypeInt(1))));
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
     * jsignalml.ASTNode$Header._accept(ASTNode.java:690)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
     * jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1136)
     * 
     */
    public class header
        extends Header
    {


        public void createParams() {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1138)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("header.createParams()");
        }

        public void createChannels() {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1138)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            log.debug("header.createChannels()");
        }

        public String id() {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1122)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "header";
        }

    }

}
