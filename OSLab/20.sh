#Maximum Value:
#echo "Arguments: $*"
max=$1
echo "MAX = $max"
args=( $@ )
for((i=0;i<$#;i++))
do
#echo "Value: ${args[i]}"
if [ ${args[i]} -gt $max ]
then
max=${args[i]}
fi
done
echo "Maximum value: $max"
min=$1
for((i=0;i<$#;i++))
do
if [ ${args[i]} -lt $min ]
then
min=${args[i]}
fi
done
echo "Minimum value:" $min
