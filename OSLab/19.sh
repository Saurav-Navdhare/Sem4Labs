
echo $1 $2 $3 $4 $5
echo $#
if [ $# -le 5 ] 
then
echo "Valid Arguments"
else
echo "Invalid Arguments"
fi
