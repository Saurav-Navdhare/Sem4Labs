clear
echo -n "Enter a number: "
read  n
echo -n "RESULT:"
if [ `expr $n%2`==0 ]
then
	echo "even"
else
	echo "odd"
fi
