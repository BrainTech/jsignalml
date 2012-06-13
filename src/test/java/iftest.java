import java.io.File;
import java.util.List;
import jsignalml.Builtins;
import jsignalml.Channel;
import jsignalml.ChannelSet;
import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeList;
import jsignalml.TypeString;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.FunctionParam;
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
 * parent paramClass=_param_p2
 * parent paramClass=_param_requires_builtin_strip
 * parent paramClass=_param_wrap_builtin_strip
 * parent paramClass=_param_call_wrap_builtin_strip
 * parent paramClass=_param_test_range_1
 * parent paramClass=_param_test_range_2
 * parent paramClass=_param_test_range_3
 * 
 */
public class iftest
    extends Signalml
{

    final static Logger log = new Logger(iftest.class);
    private int channelCounter = 0;
    iftest.header get_header = null;
    iftest.If_if1 get_if1 = null;
    iftest._param_p2 get_p2 = null;
    iftest._param_requires_builtin_strip get_requires_builtin_strip = null;
    iftest._param_wrap_builtin_strip get_wrap_builtin_strip = null;
    iftest._param_call_wrap_builtin_strip get_call_wrap_builtin_strip = null;
    iftest._param_test_range_1 get_test_range_1 = null;
    iftest._param_test_range_2 get_test_range_2 = null;
    iftest._param_test_range_3 get_test_range_3 = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("iftest.createParams()");
        {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1144)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            iftest.header obj = get_header();
            register("header", obj);
            obj.createParams();
        }
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            iftest.If_if1 obj = get_if1();
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
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("requires_builtin_strip", get_requires_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("wrap_builtin_strip", get_wrap_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("call_wrap_builtin_strip", get_call_wrap_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("test_range_1", get_test_range_1());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("test_range_2", get_test_range_2());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:621)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:255)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        register("test_range_3", get_test_range_3());
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:190)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:237)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
        log.debug("iftest.createChannels()");
        {
            // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
            // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1144)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            iftest.header obj = get_header();
            obj.createChannels();
        }
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1459)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
            iftest.If_if1 obj = get_if1();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:193)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
        return "iftest";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:123)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:194)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:313)
        int argc = args.length;
        if (argc< 1) {
            System.out.println("Syntax:\n\tiftest inputFile channelNr1 channelNr2 ...");
            return ;
        }
        BasicConfigurator.configure();
        iftest reader = new iftest();
        reader.open(new File(args[ 0 ]));
        reader.createParams();
        reader.createChannels();
        // System.out.print(ContextDumper.dump(reader));
        int nrOfChannelSets = reader.getNumberOfChannelSets();
        for (int k = 0; (k<nrOfChannelSets); k ++) {
            ChannelSet channelSet = reader.get_set(k);
            int nrOfChannels = channelSet.getNumberOfChannels();
            int nrOfChannelsToShow = nrOfChannels;
            System.out.println(("Input file for iftest codec: "+ args[ 0 ]));
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

    public iftest.header get_header() {
        // jsignalml.ASTNode$Header._accept(ASTNode.java:690)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1121)
        // jsignalml.JavaClassGen.headerClass(JavaClassGen.java:1141)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_header == null) {
            get_header = new iftest.header();
        }
        return get_header;
    }

    public iftest.If_if1 get_if1() {
        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:602)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1430)
        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1451)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_if1 == null) {
            get_if1 = new iftest.If_if1();
        }
        return get_if1;
    }

    public iftest._param_p2 get_p2() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_p2 == null) {
            get_p2 = new iftest._param_p2();
        }
        return get_p2;
    }

    public iftest._param_requires_builtin_strip get_requires_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_requires_builtin_strip == null) {
            get_requires_builtin_strip = new iftest._param_requires_builtin_strip();
        }
        return get_requires_builtin_strip;
    }

    public iftest._param_wrap_builtin_strip get_wrap_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_wrap_builtin_strip == null) {
            get_wrap_builtin_strip = new iftest._param_wrap_builtin_strip();
        }
        return get_wrap_builtin_strip;
    }

    public iftest._param_call_wrap_builtin_strip get_call_wrap_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_call_wrap_builtin_strip == null) {
            get_call_wrap_builtin_strip = new iftest._param_call_wrap_builtin_strip();
        }
        return get_call_wrap_builtin_strip;
    }

    public iftest._param_test_range_1 get_test_range_1() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_test_range_1 == null) {
            get_test_range_1 = new iftest._param_test_range_1();
        }
        return get_test_range_1;
    }

    public iftest._param_test_range_2 get_test_range_2() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_test_range_2 == null) {
            get_test_range_2 = new iftest._param_test_range_2();
        }
        return get_test_range_2;
    }

    public iftest._param_test_range_3 get_test_range_3() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
        if (get_test_range_3 == null) {
            get_test_range_3 = new iftest._param_test_range_3();
        }
        return get_test_range_3;
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

        iftest.If_if1 ._param_p1 get_p1 = null;

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
            {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:264)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                iftest.If_if1 .Else_gen_id_0 obj = new iftest.If_if1 .Else_gen_id_0();
                register("gen_id_0", obj);
                obj.createParams();
            }
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
            {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1501)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:271)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                iftest.If_if1 .Else_gen_id_0 obj = new iftest.If_if1 .Else_gen_id_0();
                obj.createChannels();
            }
        }

        public boolean hasElseIf() {
            return false;
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

        public iftest.If_if1 ._param_p1 get_p1() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
            if (get_p1 == null) {
                get_p1 = new iftest.If_if1 ._param_p1();
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
        public class Else_gen_id_0
            extends ConditionalClass.ElseBranchClass
        {

            iftest.If_if1 .Else_gen_id_0 ._param_p1 get_p1 = null;

            public void createParams() {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1485)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1504)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:248)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:39)
                log.debug("Else_gen_id_0.createParams()");
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
                log.debug("Else_gen_id_0.createChannels()");
            }

            public String id() {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:675)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1487)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
                return "gen_id_0";
            }

            public iftest.If_if1 .Else_gen_id_0 ._param_p1 get_p1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:618)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:1290)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:1300)
                if (get_p1 == null) {
                    get_p1 = new iftest.If_if1 .Else_gen_id_0 ._param_p1();
                }
                return get_p1;
            }


            /**
             * 
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
             * node.type=TypeString
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
                    // node.type=TypeString
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(333));
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
                return ((TypeInt) new TypeInt(333));
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
     * node.type=unknown
     * --> nodetype=unknown
     * 
     */
    public class _param_call_wrap_builtin_strip
        extends Param<Type>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "call_wrap_builtin_strip";
        }

        protected Type _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=unknown
            // --> nodetype=unknown
            return get_wrap_builtin_strip().get().call(new TypeString(" bbb "));
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
            return ((TypeInt) get_if1().get().access("p1").mul(new TypeInt(2)));
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
     * --> nodetype=TypeString
     * 
     */
    public class _param_requires_builtin_strip
        extends Param<TypeString>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "requires_builtin_strip";
        }

        protected TypeString _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeString
            // --> nodetype=TypeString
            return ((TypeString) Builtins.strip().call(new TypeString("aaa ")));
        }

    }


    /**
     * 
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=unknown
     * --> nodetype=TypeList
     * 
     */
    public class _param_test_range_1
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "test_range_1";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(30)));
        }

    }


    /**
     * 
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=unknown
     * --> nodetype=TypeList
     * 
     */
    public class _param_test_range_2
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "test_range_2";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(3), new TypeInt(30)));
        }

    }


    /**
     * 
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:549)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:614)
     * node.type=unknown
     * --> nodetype=TypeList
     * 
     */
    public class _param_test_range_3
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "test_range_3";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:552)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:992)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(2), new TypeInt(30), new TypeInt(5)));
        }

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
    public class _param_wrap_builtin_strip
        extends FunctionParam<TypeString>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:550)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:724)
            return "wrap_builtin_strip";
        }

        public iftest._param_wrap_builtin_strip get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:554)
            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:1014)
            return this;
        }

        public TypeString call(TypeString s) {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:555)
            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:1047)
            // node.type=unknown
            // node.expr=(strip(s))
            // node.expr.type=TypeString
            // --> nodetype=TypeString
            return ((TypeString) Builtins.strip().call(s));
        }

        public TypeString call(List<Type> args) {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:555)
            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:1057)
            if (args.size()!= 1) {
                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
            }
            return this.call(((TypeString) args.get(0)));
        }

        public String call_p(String s) {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:340)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:39)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:557)
            // jsignalml.JavaClassGen.callExprMethod_p(JavaClassGen.java:1095)
            // node.type=unknown
            // node.expr=(strip(s))
            // node.expr.type=TypeString
            // --> nodetype=TypeString
            return Builtins.strip().call(s);
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
