

print("INICIANT SCRIPT");

conn = new Mongo("localhost");
db = conn.getDB("whatsapp");

db.dropDatabase();

db.createCollection("usuaris");
db.createCollection("missatges");
db.createCollection("grups");

print("***********crean llibres*********");


usuari1 = {

    "_id" : "0",
    "usuari" : "eric",
    "contrassenya" : "123",
    "admin" : true,
    "xats" : ['0'],
    "img" : null

}

usuari2 = {

    "_id" : "1",
    "usuari" : "oleg",
    "contrassenya" : "123",
    "admin" : false,
    "xats" : ['1'],
    "img" : null

}

xat1 = {

    "_id" : "0",
    "nom" : "PUBLIC"

}

xat2 = {

    "_id" : "1",
    "nom" : "OFICINA"

}



print("***********guardant llibres*********");
db.usuaris.insertOne(usuari1);
db.usuaris.insertOne(usuari2);
db.grups.insertOne(xat1);
db.grups.insertOne(xat2);
print("SCRIPT FINALITZAT");
