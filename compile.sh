
BASEDIR=$(dirname "$0")


SRC_FOLDER=$BASEDIR/src
OUT_FOLDER=$BASEDIR/out
PACKAGE_PATH=pl/jade

CP=".:$SRC_FOLDER:$OUT_FOLDER:$CLASSPATH"

mkdir -p $OUT_FOLDER

JAVAC_COMMAND="javac --release 8 -Xlint:unchecked -cp $CP -d $OUT_FOLDER"

$JAVAC_COMMAND $(find $SRC_FOLDER -name "*.java")
