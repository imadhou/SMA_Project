
BASEDIR=$(dirname "$0")


OUT_FOLDER=$BASEDIR/out

CP=".:$SRC_FOLDER:$OUT_FOLDER:$CLASSPATH"


shift
java -cp $CP jade.Boot "$@"
