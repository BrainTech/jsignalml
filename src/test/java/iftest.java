import java.io.File;
import java.nio.FloatBuffer;
import java.util.List;

import jsignalml.Builtins;
import jsignalml.ContextDumper;
import jsignalml.Type;
import jsignalml.TypeInt;
import jsignalml.TypeList;
import jsignalml.TypeString;
import jsignalml.codec.ConditionalClass;
import jsignalml.codec.FunctionParam;
import jsignalml.codec.Param;
import jsignalml.codec.Signalml;
import jsignalml.logging.Logger;

import org.apache.log4j.BasicConfigurator;


/**
 *
 * jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
 * jsignalml.JavaClassGen.visit(JavaClassGen.java:171)
 * XXXparent paramClass=_param_p2
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=unknown
 * XXXparent paramClass=_param_requires_builtin_strip
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=TypeString
 * XXXparent paramClass=_param_wrap_builtin_strip
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=TypeString
 * XXXparent paramClass=_param_call_wrap_builtin_strip
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=unknown
 * XXXparent paramClass=_param_test_range_1
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=TypeList
 * XXXparent paramClass=_param_test_range_2
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=TypeList
 * XXXparent paramClass=_param_test_range_3
 * XXXparent node.type=unknown
 * XXXparent --> nodetype=TypeList
 *
 */
public class iftest
    extends Signalml
{

    final static Logger log = new Logger(iftest.class);
    iftest.If_if1 get_if1 = null;
    iftest._param_p2 get_p2 = null;
    iftest._param_requires_builtin_strip get_requires_builtin_strip = null;
    iftest._param_wrap_builtin_strip get_wrap_builtin_strip = null;
    iftest._param_call_wrap_builtin_strip get_call_wrap_builtin_strip = null;
    iftest._param_test_range_1 get_test_range_1 = null;
    iftest._param_test_range_2 get_test_range_2 = null;
    iftest._param_test_range_3 get_test_range_3 = null;

    public void createParams() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        log.debug("iftest.createParams()");
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1053)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            iftest.If_if1 obj = get_if1();
            register("if1", obj);
            obj.createParams();
        }
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("p2", get_p2());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("requires_builtin_strip", get_requires_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("wrap_builtin_strip", get_wrap_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("call_wrap_builtin_strip", get_call_wrap_builtin_strip());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("test_range_1", get_test_range_1());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("test_range_2", get_test_range_2());
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
        // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        register("test_range_3", get_test_range_3());
    }

    public void createChannels() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:176)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
        // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
        // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
        log.debug("iftest.createChannels()");
        {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1053)
            // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            iftest.If_if1 obj = get_if1();
            obj.createChannels();
        }
    }

    public String id() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:179)
        // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
        return "iftest";
    }

    public static void main(String... args) {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:180)
        // jsignalml.JavaClassGen.mainMethod(JavaClassGen.java:298)
        BasicConfigurator.configure();
        iftest reader = new iftest();
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

    public String getFormatDescription() {
        // jsignalml.ASTNode$Signalml._accept(ASTNode.java:121)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:182)
        // jsignalml.JavaClassGen.getFormatDescriptionMethod(JavaClassGen.java:362)
        return null;
    }

    public String getFormatID() {
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

    public iftest.If_if1 get_if1() {
        // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
        // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1045)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_if1 == null) {
            get_if1 = new iftest.If_if1();
        }
        return get_if1;
    }

    public iftest._param_p2 get_p2() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_p2 == null) {
            get_p2 = new iftest._param_p2();
        }
        return get_p2;
    }

    public iftest._param_requires_builtin_strip get_requires_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_requires_builtin_strip == null) {
            get_requires_builtin_strip = new iftest._param_requires_builtin_strip();
        }
        return get_requires_builtin_strip;
    }

    public iftest._param_wrap_builtin_strip get_wrap_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_wrap_builtin_strip == null) {
            get_wrap_builtin_strip = new iftest._param_wrap_builtin_strip();
        }
        return get_wrap_builtin_strip;
    }

    public iftest._param_call_wrap_builtin_strip get_call_wrap_builtin_strip() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_call_wrap_builtin_strip == null) {
            get_call_wrap_builtin_strip = new iftest._param_call_wrap_builtin_strip();
        }
        return get_call_wrap_builtin_strip;
    }

    public iftest._param_test_range_1 get_test_range_1() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_test_range_1 == null) {
            get_test_range_1 = new iftest._param_test_range_1();
        }
        return get_test_range_1;
    }

    public iftest._param_test_range_2 get_test_range_2() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_test_range_2 == null) {
            get_test_range_2 = new iftest._param_test_range_2();
        }
        return get_test_range_2;
    }

    public iftest._param_test_range_3 get_test_range_3() {
        // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
        // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
        // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
        // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
        // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
        if (get_test_range_3 == null) {
            get_test_range_3 = new iftest._param_test_range_3();
        }
        return get_test_range_3;
    }


    /**
     *
     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
     * jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1040)
     * jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:1025)
     * XXXparent paramClass=_param_p1
     * XXXparent node.type=TypeInt
     * XXXparent --> nodetype=TypeInt
     *
     */
    public class If_if1
        extends ConditionalClass
    {

        iftest.If_if1 ._param_p1 get_p1 = null;

        public void createParamsIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createParamsIf()");
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
            // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            register("p1", get_p1());
        }

        public void createChannelsIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:288)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createChannelsIf()");
        }

        public void createParamsElseIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createParamsElseIf()");
        }

        public void createChannelsElseIf() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:289)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createChannelsElseIf()");
        }

        public void createParamsElse() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createParamsElse()");
            {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1110)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1159)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:249)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                iftest.If_if1 .Else_gen_id_0 obj = new iftest.If_if1 .Else_gen_id_0();
                register("gen_id_0", obj);
                obj.createParams();
            }
        }

        public void createChannelsElse() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1024)
            // jsignalml.JavaClassGen.conditionalClass(JavaClassGen.java:1042)
            // jsignalml.JavaClassGen$MetadataIfBranch.<init>(JavaClassGen.java:290)
            // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
            // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
            log.debug("If_if1.createChannelsElse()");
            {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1110)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1159)
                // jsignalml.JavaClassGen$Metadata.registerContext(JavaClassGen.java:256)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                iftest.If_if1 .Else_gen_id_0 obj = new iftest.If_if1 .Else_gen_id_0();
                obj.createChannels();
            }
        }

        public boolean hasElseIf() {
            return false;
        }

        public String id() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1026)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "if1";
        }

        public Type getCondition() {
            // jsignalml.ASTNode$Conditional._accept(ASTNode.java:549)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:1027)
            // jsignalml.JavaClassGen.conditionMethod(JavaClassGen.java:1097)
            Type test = new TypeInt(1);
            return test;
        }

        public iftest.If_if1 ._param_p1 get_p1() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
            // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
            // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
            // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
            if (get_p1 == null) {
                get_p1 = new iftest.If_if1 ._param_p1();
            }
            return get_p1;
        }


        /**
         *
         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1110)
         * jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1161)
         * jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:1111)
         * XXXparent paramClass=_param_p1
         * XXXparent node.type=TypeString
         * XXXparent --> nodetype=TypeInt
         *
         */
        public class Else_gen_id_0
            extends ConditionalClass.ElseBranchClass
        {

            iftest.If_if1 .Else_gen_id_0 ._param_p1 get_p1 = null;

            public void createParams() {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1110)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:202)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                log.debug("Else_gen_id_0.createParams()");
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:452)
                // jsignalml.JavaClassGen$Metadata.registerParam(JavaClassGen.java:232)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                register("p1", get_p1());
            }

            public void createChannels() {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1110)
                // jsignalml.JavaClassGen.elseBranchClass(JavaClassGen.java:1162)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:225)
                // jsignalml.JavaClassGen$Metadata.<init>(JavaClassGen.java:214)
                // jsignalml.JavaClassGen.access$000(JavaClassGen.java:38)
                log.debug("Else_gen_id_0.createChannels()");
            }

            public String id() {
                // jsignalml.ASTNode$ElseBranch._accept(ASTNode.java:622)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:1112)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
                return "gen_id_0";
            }

            public iftest.If_if1 .Else_gen_id_0 ._param_p1 get_p1() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
                // jsignalml.JavaClassGen.paramClass(JavaClassGen.java:449)
                // jsignalml.JavaClassGen.classCacheMethod(JavaClassGen.java:884)
                // jsignalml.JavaClassGen._cacheMethod(JavaClassGen.java:894)
                if (get_p1 == null) {
                    get_p1 = new iftest.If_if1 .Else_gen_id_0 ._param_p1();
                }
                return get_p1;
            }


            /**
             *
             * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
             * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
             * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
             * node.type=TypeString
             * --> nodetype=TypeInt
             *
             */
            public class _param_p1
                extends Param<TypeInt>
            {


                public String id() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                    // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
                    return "p1";
                }

                protected TypeInt _get() {
                    // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                    // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                    // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
                    // node.type=TypeString
                    // node.expr.type=TypeInt
                    // --> nodetype=TypeInt
                    return ((TypeInt) new TypeInt(333));
                }

            }

        }


        /**
         *
         * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
         * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
         * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
         * node.type=TypeInt
         * --> nodetype=TypeInt
         *
         */
        public class _param_p1
            extends Param<TypeInt>
        {


            public String id() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
                // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
                return "p1";
            }

            protected TypeInt _get() {
                // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
                // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
                // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
                // node.type=TypeInt
                // node.expr.type=TypeInt
                // --> nodetype=TypeInt
                return ((TypeInt) new TypeInt(333));
            }

        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=unknown
     *
     */
    public class _param_call_wrap_builtin_strip
        extends Param<Type>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "call_wrap_builtin_strip";
        }

        protected Type _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=unknown
            // --> nodetype=unknown
            return get_wrap_builtin_strip().get().call(new TypeString(" bbb "));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=unknown
     *
     */
    public class _param_p2
        extends Param<Type>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "p2";
        }

        protected Type _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=unknown
            // --> nodetype=unknown
            return get_if1().get().access("p1").mul(new TypeInt(2));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=TypeString
     *
     */
    public class _param_requires_builtin_strip
        extends Param<TypeString>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "requires_builtin_strip";
        }

        protected TypeString _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=TypeString
            // --> nodetype=TypeString
            return ((TypeString) Builtins.strip().call(new TypeString("aaa ")));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=TypeList
     *
     */
    public class _param_test_range_1
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "test_range_1";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(30)));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=TypeList
     *
     */
    public class _param_test_range_2
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "test_range_2";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(3), new TypeInt(30)));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=TypeList
     *
     */
    public class _param_test_range_3
        extends Param<TypeList>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "test_range_3";
        }

        protected TypeList _get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:393)
            // jsignalml.JavaClassGen.getExprMethod(JavaClassGen.java:631)
            // node.type=unknown
            // node.expr.type=TypeList
            // --> nodetype=TypeList
            return ((TypeList) Builtins.range().call(new TypeInt(2), new TypeInt(30), new TypeInt(5)));
        }

    }


    /**
     *
     * jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
     * jsignalml.JavaClassGen.visit(JavaClassGen.java:390)
     * jsignalml.JavaClassGen.paramClass(JavaClassGen.java:445)
     * node.type=unknown
     * --> nodetype=TypeString
     *
     */
    public class _param_wrap_builtin_strip
        extends FunctionParam<TypeString>
    {


        public String id() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:391)
            // jsignalml.JavaClassGen.idMethod(JavaClassGen.java:460)
            return "wrap_builtin_strip";
        }

        public iftest._param_wrap_builtin_strip get() {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:395)
            // jsignalml.JavaClassGen.getThisMethod(JavaClassGen.java:653)
            return this;
        }

        public TypeString call(TypeString s) {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:680)
            // node.type=unknown
            // node.expr=(strip(s))
            // node.expr.type=TypeString
            // --> nodetype=TypeString
            return ((TypeString) Builtins.strip().call(s));
        }

        public TypeString call(List<Type> args) {
            // jsignalml.ASTNode$ExprParam._accept(ASTNode.java:318)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:38)
            // jsignalml.JavaClassGen.visit(JavaClassGen.java:396)
            // jsignalml.JavaClassGen.callExprMethod(JavaClassGen.java:690)
            if (args.size()!= 1) {
                throw new jsignalml.ExpressionFault.ArgMismatch(args.size(), 1);
            }
            return this.call(((TypeString) args.get(0)));
        }

    }

}
