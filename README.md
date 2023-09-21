

int int32 \
long int64 \
float float \
double double \
boolean bool \
String string \
byte[] bytes\


`message Person{
    string name =1;
    int32 age = 2;
    repeated Car car;
}`   


`Person sam  = Person.newBuilder()
                .setName("Sam")
                .setAge("26")
                .addCar(volvo)
                .addCar(BMW)
                .build();`


* THERE IS NO NULL IN PROTOBUF.

**Default value** 

ANY NUMBER TYPE default value 0\
bool false \
string empty  string \
enum first value \
repeated empty list \
map wrapper empty map \