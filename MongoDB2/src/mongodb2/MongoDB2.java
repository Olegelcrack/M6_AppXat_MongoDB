/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb2;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Sorts.descending;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import org.bson.types.ObjectId;

/**
 *
 * @author marpo
 */
public class MongoDB2 {

    /**
     * @param args the command line arguments
     */
    public static String connectionString = "mongodb://localhost:27017/ericdb";
    public static MongoClientURI uri = new MongoClientURI(connectionString);
    public static MongoClient mongoClient = new MongoClient(uri);
    public static MongoDatabase database = mongoClient.getDatabase("ericdb");
    public static MongoCollection<Document> collection = database.getCollection("autors");
    public static MongoCollection<Document> collection2 = database.getCollection("llibres");
    public static String llibres;
    public static String dada;

    public static void main(String[] args) {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n---------GESTIONAR DADES---------");
            System.out.println("1. Consultar Dades");
            System.out.println("2. Insertar Dades");
            System.out.println("3. Eliminar Dades");
            System.out.println("4. Actualitzar Dades");
            System.out.println("S. Sortir");

            String sa = sc.next();
            char opcio = sa.charAt(0); 

            switch (opcio) {
                case '1':
                    consultarDades();
                    break;
                case '2':
                    insertarDades();
                    break;
                case '3':
                    eliminarDades();
                    break;
                case '4':
                    actualitzarDades();
                    break;
                case 's':
                    System.out.println(" Sortir");
                    //persistirDadesClub();
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    //persistirDadesClub();
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        
        } while (!sortir);
        
         
        
        
        

    }
    
    public static void consultarDades() {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********VISUALITZAR DADES**********");
            System.out.println("1. Visualitzar autors");
            System.out.println("2. Visualitzar llibres");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    visualitzarAutors();
                    break;
                case '2':
                    visualitzarLlibres();
                    break;

                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
    
    public static void insertarDades() {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********INSERTAR DADES**********");
            System.out.println("1. Insertar un Autor");
            System.out.println("2. Insertar un Llibre");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    insertarAutor();
                    break;
                case '2':
                    insertarLlibre();
                    break;

                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
    
    public static void eliminarDades() {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********ELIMINAR DADES**********");
            System.out.println("1. Eliminar un Autor");
            System.out.println("2. Eliminar un Llibre");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    eliminarAutor();
                    break;
                case '2':
                    eliminarLlibre();
                    break;

                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
    
    public static void actualitzarDades() {
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********ACTUALITZAR DADES**********");
            System.out.println("1. Actualitzar un Autor");
            System.out.println("2. Actualitzar un Llibre");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    actualitzarAutor();
                    break;
                case '2':
                    actualitzarLlibre();
                    break;

                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
    
    
    public static void insertarAutor(){
        Scanner sc = new Scanner(System.in);
        try {
            String autor;
            Document result = new Document();
            String naixement;
            String defuncio;
            int edat = 0;
            boolean edadCorrecta = false;
            String sino;
            String llibres = "";
            do {
                System.out.println("--------------------------------");
                System.out.print("Escriu el nom del Autor: ");
                autor = sc.nextLine();
            } while (autor.trim().isEmpty());
            do {
                System.out.println("--------------------------------");
                System.out.print("Vols afegir un llibre creat al autor? (S / N)");
                sino = sc.nextLine();
                
                if(sino.equalsIgnoreCase("S")){
                    System.out.println("--------------------------------");
                    System.out.print("Quin llibre vols afegir al Autor (_id): ");
                    String llibre = sc.nextLine();
                    result = collection2.find(eq("_id", llibre)).first();
                    if(result != null){
                        llibres = result.get("_id").toString();
                    } else {
                        System.out.println("****************************************");
                        System.out.println("No s'ha trobat cap llibre amb aquest id.");
                        System.out.println("****************************************");
                    }
                }
            }while (result == null || sino.equalsIgnoreCase("N"));
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el lloc de naixement: ");
                naixement = sc.nextLine();
                if(naixement.trim().isEmpty()){
                    System.out.println("****************************************");
                    System.out.println("El lloc de naixement no pot estar buit");
                    System.out.println("****************************************");
                }
            } while(naixement.trim().isEmpty());
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el lloc de defunció: ");
                defuncio = sc.nextLine();
                if(defuncio.trim().isEmpty()){
                    System.out.println("****************************************");
                    System.out.println("El lloc de defunció no pot estar buit");
                    System.out.println("****************************************");
                }
            } while(defuncio.trim().isEmpty());
            while (!edadCorrecta) {
                try {
                    System.out.println("--------------------------------");
                    System.out.print("Escriu la seva edat: ");
                    edat = sc.nextInt();
                    sc.nextLine();
                    edadCorrecta = true;
                } catch (InputMismatchException e) {
                    System.out.println("****************************************");
                    System.out.println("Error: La edat ha de ser un número enter.");
                    System.out.println("****************************************");
                    sc.nextLine();
                }
            }
            Document maxIdDoc = collection.find().sort(descending("_id")).limit(1).first();
            Object maxId = maxIdDoc.get("_id");
            int num = Integer.parseInt(maxId.toString());
            num++;
            maxId = Integer.toString(num);

            Document newDocument = new Document("_id", maxId);
            newDocument.append("llibres", Arrays.asList(llibres));
            newDocument.append("nom", autor);
            newDocument.append("naixement", naixement);
            newDocument.append("defuncio", defuncio);
            newDocument.append("edat", edat);
            collection.insertOne(newDocument);
            System.out.println("Autor insertat correctament");
        } catch (Exception e) {
            System.out.println("Error: Autor no insertat");
        }
}




    
    
    public static void insertarLlibre(){
        Scanner sc = new Scanner(System.in);
        try {
            String llibre = "";
            int any = 0;
            int pagines = 0;
            double valoracio = 0;
            boolean anyCorrecte = false;
            boolean valoracioCorrecte = false;
            boolean paginesCorrecte = false;
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el nom del Llibre: ");
                llibre = sc.nextLine();
                if (llibre.trim().isEmpty()) {
                    System.out.println("****************************************");
                    System.out.println("El nom del llibre no pot estar buit.");
                    System.out.println("****************************************");
                    return;
                }
            }while(llibre.trim().isEmpty());
            
            while (!anyCorrecte) {
                try {
                    System.out.println("--------------------------------");
                    System.out.print("Escriu el seu any: ");
                    any = sc.nextInt();
                    sc.nextLine();
                    anyCorrecte = true;
                } catch (InputMismatchException e) {
                    System.out.println("****************************************");
                    System.out.println("Error: L'any ha de ser un número enter.");
                    System.out.println("****************************************");
                    sc.nextLine();
                }
            }
            while (!paginesCorrecte) {
                try {
                    System.out.println("--------------------------------");
                    System.out.print("Escriu el numero de pàgines: ");
                    pagines = sc.nextInt();
                    sc.nextLine();
                    paginesCorrecte = true;
                } catch (InputMismatchException e) {
                    System.out.println("****************************************");
                    System.out.println("Error: El numero de pagines ha de ser un número enter.");
                    System.out.println("****************************************");
                    sc.nextLine();
                }
            }
            
            while (!valoracioCorrecte) {
                try {
                    System.out.println("--------------------------------");
                    System.out.print("Escriu la valoracio del llibre: ");
                    valoracio = sc.nextDouble();
                    sc.nextLine();
                    valoracioCorrecte = true;
                } catch (InputMismatchException e) {
                    System.out.println("****************************************");
                    System.out.println("Error: La valoracio ha de ser un número enter.");
                    System.out.println("****************************************");
                    sc.nextLine();
                }
            }

            Document maxIdDoc = collection2.find().sort(descending("_id")).limit(1).first();
            Object maxId = maxIdDoc.get("_id");
            int num = Integer.parseInt(maxId.toString());
            num++;
            maxId = Integer.toString(num);
            System.out.println(maxId);

            Document newDocument = new Document("_id", maxId)
                    .append("nom", llibre)
                    .append("any", any)
                    .append("pagines", pagines)
                    .append("valoracio", valoracio)
                    .append("deleted", "false");
            collection2.insertOne(newDocument);
            System.out.println("Llibre insertat correctament");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    
    public static void eliminarAutor(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("--------------------------------");
            System.out.print("Escriu la id del autor que vols eliminar: ");
            String autorId = sc.nextLine();
            collection.deleteOne(eq("_id", autorId));
            System.out.println("Autor eliminat correctament");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: la id introduïda no és vàlida.");
        } catch (Exception e) {
            System.out.println("Error: autor no eliminat");
        }
    }
    
    public static void eliminarLlibre(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("--------------------------------");
            System.out.print("Escriu la id del llibre que vols eliminar: ");
            String llibreId = sc.nextLine();
            collection2.deleteOne(eq("_id", llibreId));
            System.out.println("Llibre eliminat correctament");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: la id introduïda no és vàlida.");
        } catch (Exception e) {
            System.out.println("Error: Llibre no eliminat");
        }
        
    }
    
    public static void actualitzarAutor(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        try {
            Document cursor4 = new Document();
            String autor = "";
            Set<String> campos;
            String noma = "";
            String naixement = "";
            String defuncio = "";
            int edat = 0;
            String id;
            Document result = new Document();
            boolean edadCorrecta = false;
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu la id del autor que vols actualitzar: ");
                autor = sc.nextLine();

                cursor4 = collection.find(eq("_id", autor)).first();
                if(cursor4==null){
                    System.out.println("****************************************");
                    System.out.println("No s'ha trobat el autor");
                    System.out.println("****************************************");
                }
            }while(cursor4==null);
            campos = cursor4.keySet();
            for (String campo : campos) {
                System.out.printf("%-20s",campo);
            }
            System.out.println("");
            System.out.println("************************************************************************************************************");
            for (Object value : cursor4.values()) {
                System.out.printf("%-20s",value);
            }
            System.out.println();
            do {
                System.out.println("--------------------------------");
                System.out.println("Quina dada vols actualitzar: ");
                System.out.println("1. Llibres");
                System.out.println("2. Nom");
                System.out.println("3. Naixement");
                System.out.println("4. Defunció");
                System.out.println("5. Edat");
                System.out.println("S. Sortir");

                String sa = sc.next();
                char opcio = sa.charAt(0);

                switch (opcio) {
                    case '1':
                        boolean sortir2 = false;
                        do{
                            System.out.println("--------------------------------");
                            System.out.print("Vols (1) Afegir un llibre o (2) Establir un: ");
                            String sa2 = sc.next();
                            char sino = sa2.charAt(0);
                            switch (sino) {
                                case '1':
                                    do{
                                        System.out.println("--------------------------------");
                                        System.out.print("Escriu el _id del llibre que vols afegir: ");
                                        id = sc.next();
                                        result = collection2.find(eq("_id", id)).first();
                                        if(result != null){
                                            llibres = result.get("_id").toString();
                                        } else {
                                            System.out.println("****************************************");
                                            System.out.println("No s'ha trobat cap llibre amb aquest id.");
                                            System.out.println("****************************************");
                                        }
                                    }while(result==null);
                                    collection.updateOne(eq("_id", autor), new Document("$push", new Document("llibres", id)));
                                    sortir2 = true;
                                    break;
                                case '2':
                                    do{
                                        System.out.println("--------------------------------");
                                        System.out.print("Escriu el _id del llibre que vols posar: ");
                                        id = sc.next();
                                        result = collection2.find(eq("_id", id)).first();
                                        if(result != null){
                                            llibres = result.get("_id").toString();
                                        } else {
                                            System.out.println("****************************************");
                                            System.out.println("No s'ha trobat cap llibre amb aquest id.");
                                            System.out.println("****************************************");
                                        }
                                    }while(result==null);
                                    collection.updateOne(eq("_id", autor), new Document("$set", new Document("llibres", Arrays.asList(id))));
                                    sortir2 = true;
                                    break;
                                default:
                                    System.out.println("Valor no vàlid");
                                }
                            } while (!sortir2);
                            for (String campo : campos) {
                                System.out.printf("%-20s",campo);
                            }
                            System.out.println("");
                            System.out.println("************************************************************************************************************");
                            cursor4 = collection.find(eq("_id", autor)).first();
                            campos = cursor4.keySet();
                            for (Object value : cursor4.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                            break;
                    case '2':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el nom del Autor: ");
                            noma = sc.nextLine();
                            if(noma.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El nom no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (noma.trim().isEmpty());
                        collection.updateOne(eq("_id", autor), new Document("$set", new Document("nom", noma)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", autor)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '3':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el nou lloc de naixement: ");
                            naixement = sc.nextLine();
                            if(naixement.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El naixment no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (naixement.trim().isEmpty());
                        collection.updateOne(eq("_id", autor), new Document("$set", new Document("naixement", naixement)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", autor)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '4':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el nou lloc de defuncio: ");
                            defuncio = sc.nextLine();
                            if(defuncio.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El lloc de defuncio no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (defuncio.trim().isEmpty());
                        collection.updateOne(eq("_id", autor), new Document("$set", new Document("naixement", defuncio)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", autor)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '5':
                        while (!edadCorrecta) {
                            try {
                                System.out.println("--------------------------------");
                                System.out.print("Escriu la seva edat: ");
                                edat = sc.nextInt();
                                sc.nextLine();
                                edadCorrecta = true;
                            } catch (InputMismatchException e) {
                                System.out.println("****************************************");
                                System.out.println("Error: La edat ha de ser un número enter.");
                                System.out.println("****************************************");
                                sc.nextLine();
                            }
                        }
                        collection.updateOne(eq("_id", autor), new Document("$set", new Document("edat", edat)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", autor)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case 's':
                        sortir = true;
                        break;
                    case 'S':
                        sortir = true;
                        break;
                    default:
                        System.out.println("Valor no vàlid");
                }
            } while (!sortir);
        }catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
    }
    
    public static void actualitzarLlibre(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        try {
            Document cursor4 = new Document();
            String llibre = "";
            Set<String> campos;
            String noml;
            int any = 0;
            int pagines = 0;
            double valoracio = 0;
            boolean anyCorrecte = false;
            boolean valoracioCorrecte = false;
            boolean paginesCorrecte = false;
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu la id del llibre que vols actualitzar: ");
                llibre = sc.nextLine();

                cursor4 = collection2.find(eq("_id", llibre)).first();
                if(cursor4==null){
                    System.out.println("****************************************");
                    System.out.println("No s'ha trobat el llibre");
                    System.out.println("****************************************");
                }
            }while(cursor4==null);
            campos = cursor4.keySet();
            for (String campo : campos) {
                System.out.printf("%-20s",campo);
            }
            System.out.println("");
            System.out.println("************************************************************************************************************");
            for (Object value : cursor4.values()) {
                System.out.printf("%-20s",value);
            }
            System.out.println();
            do{
                System.out.println("--------------------------------");
                System.out.println("Quina dada vols actualitzar: ");
                System.out.println("1. Nom");
                System.out.println("2. Any");
                System.out.println("3. Pagines");
                System.out.println("4. Valoracio");
                System.out.println("S. Sortir");
                 String sa = sc.next();
                char opcio = sa.charAt(0);     

                switch (opcio) {
                    case '1':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el nou nom del Llibre: ");
                            noml = sc.nextLine();
                            if(noml.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El nom no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (noml.trim().isEmpty());
                        collection2.updateOne(eq("_id", llibre), new Document("$set", new Document("nom", noml)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection2.find(eq("_id", llibre)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '2':
                        while (!anyCorrecte) {
                            try {
                                System.out.println("--------------------------------");
                                System.out.print("Escriu el nou any: ");
                                any = sc.nextInt();
                                sc.nextLine();
                                anyCorrecte = true;
                            } catch (InputMismatchException e) {
                                System.out.println("****************************************");
                                System.out.println("Error: L'any ha de ser un número enter.");
                                System.out.println("****************************************");
                                sc.nextLine();
                            }
                        }
                        collection2.updateOne(eq("_id", llibre), new Document("$set", new Document("any", any)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection2.find(eq("_id", llibre)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '3':
                        while (!paginesCorrecte) {
                            try {
                                System.out.println("--------------------------------");
                                System.out.print("Escriu les noves pagines: ");
                                pagines = sc.nextInt();
                                sc.nextLine();
                                paginesCorrecte = true;
                            } catch (InputMismatchException e) {
                                System.out.println("****************************************");
                                System.out.println("Error: Les pagines han de ser un número enter.");
                                System.out.println("****************************************");
                                sc.nextLine();
                            }
                        }
                        collection2.updateOne(eq("_id", llibre), new Document("$set", new Document("pagines", pagines)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection2.find(eq("_id", llibre)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '4':
                        while (!valoracioCorrecte) {
                            try {
                                System.out.println("--------------------------------");
                                System.out.print("Escriu la nova valoracio: ");
                                valoracio = sc.nextInt();
                                sc.nextLine();
                                valoracioCorrecte = true;
                            } catch (InputMismatchException e) {
                                System.out.println("****************************************");
                                System.out.println("Error: La valoracio ha de ser un número enter.");
                                System.out.println("****************************************");
                                sc.nextLine();
                            }
                        }
                        collection2.updateOne(eq("_id", llibre), new Document("$set", new Document("valoracio", valoracio)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection2.find(eq("_id", llibre)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case 's':
                        System.out.println(" Sortir");
                        sortir = true;
                        break;    
                    case 'S':
                        System.out.println(" Sortir");
                        sortir = true;
                       break;
                    default:
                        System.out.println("Valor no vàlid");
                }
            } while (!sortir);
        }catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
    }
    
    public static void visualitzarAutors(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********VISUALITZAR AUTORS**********");
            System.out.println("1. Visualitzar tots els autors");
            System.out.println("2. Visualitzar per Llibres");
            System.out.println("3. Visualitzar per Nom");
            System.out.println("4. Visualitzar per Naixement");
            System.out.println("5. Visualitzar per Defuncio");
            System.out.println("6. Visualitzar per Edat");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    MongoCursor<Document> cursor = collection.find().iterator();
                    
                    if(cursor.hasNext()){
                        Document doc = cursor.next();
                        Set<String> campos = doc.keySet();
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                    }
                        FindIterable<Document> cursor2 = collection.find();
                        for (Document doc : cursor2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    break;
                case '2':
                    System.out.println("--------------------------------");
                    System.out.println("1. Autors amb llibre igual a : ");
                    System.out.println("2. Autors amb llibre mes gran o igual a : ");
                    System.out.println("3. Autors amb llibre menys gran o igual a : ");
                    int sino = sc.nextInt();
                    System.out.println("--------------------------------");
                    System.out.print("Llibre : ");
                    String llibre = sc.next();
                    FindIterable<Document> cursor1;
                    Document llibres;
                    switch(sino){
                        case 1:
                            llibres =  collection.find(eq("llibres", llibre)).first();
                            cursor1 = collection.find(eq("llibres", llibre));
                            break;
                        case 2:
                            llibres = collection.find(gte("llibres", llibre)).first();
                            cursor1 = collection.find(gte("llibres", llibre));
                            break;
                        case 3:
                            llibres = collection.find(lte("llibres", llibre)).first();
                            cursor1 = collection.find(lte("llibres", llibre));
                            break;
                        default:
                            llibres = null;
                            cursor1 = null;
                            break;
                            
                    }
                    if(llibres!=null){    
                        Set<String> campos = llibres.keySet();
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        for (Document doc : cursor1) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("No hi han autors amb aquests llibres");
                    }
                    
                    break;
                case '3':
                    System.out.println("--------------------------------");
                    System.out.print("Autors amb Nom: ");
                    sc.nextLine();
                    String nom = sc.nextLine();
                    Pattern pattern = Pattern.compile(nom, Pattern.CASE_INSENSITIVE);
                    Document noms = collection.find(regex("nom", pattern)).first();
                    if(noms!=null){
                        Set<String> nomcampos = noms.keySet();
                        for (String campo : nomcampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> noms2 = collection.find(regex("nom", pattern));
                        for (Document doc : noms2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han autors amb aquest nom");
                        System.out.println("****************************************");
                    }
                    break;
                case '4':
                    System.out.println("--------------------------------");
                    System.out.print("Autors amb Naixement a: ");
                    sc.nextLine();
                    String naixement = sc.nextLine();
                    Pattern pattern2 = Pattern.compile(naixement, Pattern.CASE_INSENSITIVE);
                    Document naixements = collection.find(regex("nom", pattern2)).first();
                    if(naixements!=null){
                        Set<String> naixementscampos = naixements.keySet();
                        for (String campo : naixementscampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> naixements2 = collection.find(regex("naixement", pattern2));
                        for (Document doc : naixements2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han autors amb aquest lloc de naixement");
                        System.out.println("****************************************");
                    }
                    break;
                case '5':
                    System.out.println("--------------------------------");
                    System.out.print("Autors amb Defuncio a: ");
                    sc.nextLine();
                    String defuncio = sc.nextLine();
                    Pattern pattern3 = Pattern.compile(defuncio, Pattern.CASE_INSENSITIVE);
                    Document defuncions = collection.find(regex("nom", pattern3)).first();
                    if(defuncions!=null){
                        Set<String> defuncionscampos = defuncions.keySet();
                        for (String campo : defuncionscampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> defuncions2 = collection.find(regex("defuncio", pattern3));
                        for (Document doc : defuncions2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                            System.out.println("****************************************");
                            System.out.println("No hi han autors amb aquest lloc de defuncio");
                            System.out.println("****************************************");
                        }
                    break;
                case '6':
                    System.out.println("--------------------------------");
                    System.out.println("1. Autors amb edat igual a : ");
                    System.out.println("2. Autors amb edat mes gran o igual a : ");
                    System.out.println("3. Autors amb edat menys gran o igual a : ");
                    int sino2 = sc.nextInt();
                    System.out.println("--------------------------------");
                    System.out.print("Edat : ");
                    int edat = sc.nextInt();
                    FindIterable<Document> cursor3;
                    Document edats;
                    switch(sino2){
                        case 1:
                            edats =  collection.find(eq("edat", edat)).first();
                            cursor3 = collection.find(eq("edat", edat));
                            break;
                        case 2:
                            edats = collection.find(gte("edat", edat)).first();
                            cursor3 = collection.find(gte("edat", edat));
                            break;
                        case 3:
                            edats = collection.find(lte("edat", edat)).first();
                            cursor3 = collection.find(lte("edat", edat));
                            break;
                        default:
                            edats = null;
                            cursor3 = null;
                            break;
                            
                    }
                        System.out.println(sino2);
                        if(edats!=null){
                            Set<String> campos2 = edats.keySet();
                            for (String campo : campos2) {
                                System.out.printf("%-20s",campo);
                            }
                            System.out.println("");
                            System.out.println("************************************************************************************************************");
                            for (Document doc : cursor3) {
                                for (Object value : doc.values()) {
                                    System.out.printf("%-20s",value);
                                }
                                System.out.println();
                            }
                        }else{
                            System.out.println("****************************************");
                            System.out.println("No hi han autors amb aquesta edat");
                            System.out.println("****************************************");
                        }
                    break;
                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
    
    public static void visualitzarLlibres(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********VISUALITZAR LLIBRES**********");
            System.out.println("1. Visualitzar tots els llibres");
            System.out.println("2. Visualitzar llibres per nom");
            System.out.println("3. Visualitzar llibres per any");
            System.out.println("4. Visualitzar llibres per pagines");
            System.out.println("5. Visualitzar llibres per valoracio");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    MongoCursor<Document> cursor3 = collection2.find().iterator();
                    
                    if(cursor3.hasNext()){
                        Document doc = cursor3.next();
                        Set<String> campos = doc.keySet();
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                    }
                        FindIterable<Document> cursor4 = collection2.find();
                        for (Document doc : cursor4) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    break;
                case '2':
                    System.out.println("--------------------------------");
                    System.out.print("Autors amb Nom: ");
                    sc.nextLine();
                    String nom = sc.nextLine();
                    Pattern pattern = Pattern.compile(nom, Pattern.CASE_INSENSITIVE);
                    Document noms = collection2.find(regex("nom", pattern)).first();
                    if(noms!=null){
                        Set<String> nomcampos = noms.keySet();
                        for (String campo : nomcampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> noms2 = collection2.find(regex("nom", pattern));
                        for (Document doc : noms2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han llibres amb aquesta nom");
                        System.out.println("****************************************");
                    }
                    break;
                case '3':
                    System.out.println("--------------------------------");
                    System.out.println("1. Autors amb any igual a : ");
                    System.out.println("2. Autors amb any mes gran o igual a : ");
                    System.out.println("3. Autors amb any menys gran o igual a : ");
                    int sino2 = sc.nextInt();
                    System.out.println("--------------------------------");
                    System.out.print("Any : ");
                    int any = sc.nextInt();
                    FindIterable<Document> cursor;
                    Document anys;
                    switch(sino2){
                        case 1:
                            anys =  collection2.find(eq("any", any)).first();
                            cursor = collection2.find(eq("any", any));
                            break;
                        case 2:
                            anys = collection2.find(gte("any", any)).first();
                            cursor = collection2.find(gte("any", any));
                            break;
                        case 3:
                            anys = collection2.find(lte("any", any)).first();
                            cursor = collection2.find(lte("any", any));
                            break;
                        default:
                            anys = null;
                            cursor = null;
                            break;
                            
                    }
                    if(anys!=null){
                        Set<String> campos = anys.keySet();
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        for (Document doc : cursor) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han llibres amb aquesta any");
                        System.out.println("****************************************");
                    }
                    break;
                case '4':
                    System.out.println("--------------------------------");
                    System.out.println("1. Autors amb pagines igual a : ");
                    System.out.println("2. Autors amb pagines mes gran o igual a : ");
                    System.out.println("3. Autors amb pagines menys gran o igual a : ");
                    int sino = sc.nextInt();
                    System.out.println("--------------------------------");
                    System.out.print("Pagines : ");
                    int pagines = sc.nextInt();
                    FindIterable<Document> cursor1;
                    Document pagines1;
                    switch(sino){
                        case 1:
                            pagines1 =  collection2.find(eq("pagines", pagines)).first();
                            cursor1 = collection2.find(eq("pagines", pagines));
                            break;
                        case 2:
                            pagines1 = collection2.find(gte("pagines", pagines)).first();
                            cursor1 = collection2.find(gte("pagines", pagines));
                            break;
                        case 3:
                            pagines1 = collection2.find(lte("pagines", pagines)).first();
                            cursor1 = collection2.find(lte("pagines", pagines));
                            break;
                        default:
                            pagines1 = null;
                            cursor1 = null;
                            break;
                            
                    }
                    if(pagines1!=null){    
                        Set<String> campos1 = pagines1.keySet();
                        for (String campo : campos1) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        for (Document doc : cursor1) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han llibres amb aquestes pagines");
                        System.out.println("****************************************");
                    }
                    break;
                case '5':
                    System.out.println("--------------------------------");
                    System.out.println("1. Autors amb valoracio igual a : ");
                    System.out.println("2. Autors amb valoracio mes gran o igual a : ");
                    System.out.println("3. Autors amb valoracio menys gran o igual a : ");
                    int sino3 = sc.nextInt();
                    System.out.println("--------------------------------");
                    System.out.print("Valoracio : ");
                    int valoracio = sc.nextInt();
                    FindIterable<Document> cursor2;
                    Document valoracions;
                    switch(sino3){
                        case 1:
                            valoracions =  collection2.find(eq("valoracio", valoracio)).first();
                            cursor2 = collection2.find(eq("valoracio", valoracio));
                            break;
                        case 2:
                            valoracions = collection2.find(gte("valoracio", valoracio)).first();
                            cursor2 = collection2.find(gte("valoracio", valoracio));
                            break;
                        case 3:
                            valoracions = collection2.find(lte("valoracio", valoracio)).first();
                            cursor2 = collection2.find(lte("valoracio", valoracio));
                            break;
                        default:
                            valoracions = null;
                            cursor2 = null;
                            break;
                            
                    }
                    if(valoracions!=null){    
                        Set<String> campos3 = valoracions.keySet();
                        for (String campo : campos3) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        for (Document doc : cursor2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han llibres amb aquesta valoracio");
                        System.out.println("****************************************");
                    }
                    break;
                case 's':
                    System.out.println(" Sortir");
                    sortir = true;
                    break;    
                case 'S':
                    System.out.println(" Sortir");
                    sortir = true;
                   break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }
}
