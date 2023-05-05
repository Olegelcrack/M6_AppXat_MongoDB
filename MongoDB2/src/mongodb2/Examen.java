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
public class Examen {

    /**
     * @param args the command line arguments
     */
    public static String connectionString = "mongodb://localhost:27017/ericdb";
    public static MongoClientURI uri = new MongoClientURI(connectionString);
    public static MongoClient mongoClient = new MongoClient(uri);
    public static MongoDatabase database = mongoClient.getDatabase("examendb");
    public static MongoCollection<Document> collection = database.getCollection("portatils");
    public static MongoCollection<Document> collection2 = database.getCollection("alumnes");
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
            System.out.println("1. Visualitzar portatils");
            System.out.println("2. Visualitzar alumnes");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    visualitzarPortatils();
                    break;
                case '2':
                    visualitzarAlumnes();
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
            System.out.println("1. Insertar un Portatil");
            System.out.println("2. Insertar un Alumne");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    insertarPortatil();
                    break;
                case '2':
                    insertarAlumne();
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
            System.out.println("1. Eliminar un Portatil");
            System.out.println("2. Eliminar un Alumne");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    eliminarPortatil();
                    break;
                case '2':
                    eliminarAlumne();
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
            System.out.println("1. Actualitzar un Portatil");
            System.out.println("2. Actualitzar un Alumne");
            System.out.println("S. Sortir");

             String sa = sc.next();
            char opcio = sa.charAt(0);     

            switch (opcio) {
                case '1':
                    actualitzarPortatil();
                    break;
                case '2':
                    actualitzarAlumne();
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
    
    
    public static void insertarPortatil(){
        Scanner sc = new Scanner(System.in);
        try {
            String portatil;
            Document result = new Document();
            String marca;
            String model;
            String ref;
            do{
                System.out.println("--------------------------------");
                System.out.print("Quina referencia vols posar al Portatil: ");
                ref = sc.nextLine();
                result = collection.find(eq("ref", ref)).first();
                if(result == null){
                    
                } else {
                    System.out.println("****************************************");
                    System.out.println("Ja existeix un Portatil amb aquesta Referencia.");
                    System.out.println("****************************************");
                    ref = "";
                }
            }while (result != null);
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu la marca: ");
                marca = sc.nextLine();
                if(marca.trim().isEmpty()){
                    System.out.println("****************************************");
                    System.out.println("La marca no pot estar buida");
                    System.out.println("****************************************");
                }
            } while(marca.trim().isEmpty());
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el Model ");
                model = sc.nextLine();
                if(model.trim().isEmpty()){
                    System.out.println("****************************************");
                    System.out.println("El model no pot estar buit");
                    System.out.println("****************************************");
                }
            } while(model.trim().isEmpty());
            
            Document maxIdDoc = collection.find().sort(descending("_id")).limit(1).first();
            Object maxId = maxIdDoc.get("_id");
            int num = Integer.parseInt(maxId.toString());
            num++;
            maxId = Integer.toString(num);

            Document newDocument = new Document("_id", maxId);
            newDocument.append("ref", ref);
            newDocument.append("marca", marca);
            newDocument.append("model", model);
            collection.insertOne(newDocument);
            System.out.println("Portatil insertat correctament");
        } catch (Exception e) {
            System.out.println("Error: Portatil no insertat");
        }
}




    
    
    public static void insertarAlumne(){
        Scanner sc = new Scanner(System.in);
        try {
            String alumne ;
            Document result = new Document();
            String DNI;
            String cognom;
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el nom del Alumne: ");
                alumne = sc.nextLine();
                if (alumne.trim().isEmpty()) {
                    System.out.println("****************************************");
                    System.out.println("El nom del Alumne no pot estar buit.");
                    System.out.println("****************************************");
                    return;
                }
            }while(alumne.trim().isEmpty());
            do{
                System.out.println("--------------------------------");
                System.out.print("Quin DNI vols posar al Alumne: ");
                DNI = sc.nextLine();
                result = collection2.find(eq("DNI", DNI)).first();
                if(result == null){
                    
                } else {
                    System.out.println("****************************************");
                    System.out.println("Ja existeix un Alumne amb aquest DNI.");
                    System.out.println("****************************************");
                    DNI = "";
                }
            }while (result != null);
            
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el cognom: ");
                cognom = sc.nextLine();
                if(cognom.trim().isEmpty()){
                    System.out.println("****************************************");
                    System.out.println("El cognom no pot estar buida");
                    System.out.println("****************************************");
                }
            } while(cognom.trim().isEmpty());

            Document maxIdDoc = collection2.find().sort(descending("_id")).limit(1).first();
            Object maxId = maxIdDoc.get("_id");
            int num = Integer.parseInt(maxId.toString());
            num++;
            maxId = Integer.toString(num);
            System.out.println(maxId);

            Document newDocument = new Document("_id", maxId)
                    .append("DNI", DNI)
                    .append("nom", alumne)
                    .append("cognom", cognom)
                    .append("mòduls", "")
                    .append("portatil", "");
            collection2.insertOne(newDocument);
            System.out.println("Alumne insertat correctament");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    
    public static void eliminarPortatil(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("--------------------------------");
            System.out.print("Escriu la REF del portatil que vols eliminar: ");
            String REF = sc.nextLine();
            collection.deleteOne(eq("ref", REF));
            System.out.println("Portatil eliminat correctament");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: la REF introduïda no és vàlida.");
        } catch (Exception e) {
            System.out.println("Error: Portatil no eliminat");
        }
    }
    
    public static void eliminarAlumne(){
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
    
    public static void actualitzarPortatil(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        try {
            Document cursor4 = new Document();
            Document cursor5 = new Document();
            String autor = "";
            Set<String> campos;
            Object valors;
            String REF = "";
            String marca = "";
            String model = "";
            char portatil;
            String id;
            String DNI;
            Document result = new Document();
            boolean edadCorrecta = false;
            do{
                System.out.println("--------------------------------");
                System.out.print("Escriu el DNI del Alumne amb el portatil que vols modficar: ");
                DNI = sc.nextLine();

                cursor4 = collection2.find(eq("DNI", DNI)).first();
                if(cursor4==null){
                    System.out.println("****************************************");
                    System.out.println("No s'ha trobat el Alumne");
                    System.out.println("****************************************");
                }
            }while(cursor4==null);
            campos = cursor4.keySet();
            
            for (String campo : campos) {
                System.out.printf("%-20s",campo);
            }
            valors = cursor4.values();
            System.out.println("");
            System.out.println("************************************************************************************************************");
            for (Object value : cursor4.values()) {
                System.out.printf("%-20s",value);
            }
            portatil = valors.toString().charAt(6);
            cursor5 = collection.find(eq("_id", portatil)).first();
            System.out.println("");
             System.out.println("");
            campos = cursor5.keySet();
            for (String campo : campos) {
                System.out.printf("%-20s",campo);
            }
            System.out.println("");
            System.out.println("************************************************************************************************************");
            
            for (Object value : cursor5.values()) {
                System.out.printf("%-20s",value);
            }
            System.out.println("");
            do {
                System.out.println("--------------------------------");
                System.out.println("Quina dada vols actualitzar: ");
                System.out.println("1. REF");
                System.out.println("2. Marca");
                System.out.println("3. Model");
                System.out.println("S. Sortir");

                String sa = sc.next();
                char opcio = sa.charAt(0);

                switch (opcio) {
                    case '1':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el REF del Portatil: ");
                            REF = sc.nextLine();
                            if(REF.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El REF no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (REF.trim().isEmpty());
                        collection.updateOne(eq("_id", portatil), new Document("$set", new Document("ref", REF)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", portatil)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '2':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu la Marca del Portatil: ");
                            marca = sc.nextLine();
                            if(marca.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("La Marca no pot estar buida");
                                System.out.println("****************************************");
                            }
                        } while (marca.trim().isEmpty());
                        collection.updateOne(eq("_id", portatil), new Document("$set", new Document("marca", marca)));
                        campos = cursor4.keySet();
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", portatil)).first();
                        campos = cursor4.keySet();
                        for (Object value : cursor4.values()) {
                            System.out.printf("%-20s",value);
                        }
                        System.out.println();
                        break;
                    case '3':
                        do {
                            System.out.println("--------------------------------");
                            System.out.print("Escriu el model del portatil: ");
                            model = sc.nextLine();
                            if(model.trim().isEmpty()){
                                System.out.println("****************************************");
                                System.out.println("El model no pot estar buit");
                                System.out.println("****************************************");
                            }
                        } while (model.trim().isEmpty());
                        collection.updateOne(eq("_id", portatil), new Document("$set", new Document("model", model)));
                        for (String campo : campos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        cursor4 = collection.find(eq("_id", portatil)).first();
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
    
    public static void actualitzarAlumne(){
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
    
    public static void visualitzarPortatils(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********VISUALITZAR PORTATILS**********");
            System.out.println("1. Visualitzar tots els portatils");
            System.out.println("2. Visualitzar per REF");
            System.out.println("3. Visualitzar per Marca");
            System.out.println("4. Visualitzar per Model");
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
                    System.out.print("Portatils amb REF: ");
                    sc.nextLine();
                    String ref = sc.nextLine();
                    Pattern pattern = Pattern.compile(ref, Pattern.CASE_INSENSITIVE);
                    Document refs = collection.find(regex("ref", pattern)).first();
                    if(refs!=null){
                        Set<String> nomcampos = refs.keySet();
                        for (String campo : nomcampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> refs2 = collection.find(regex("ref", pattern));
                        for (Document doc : refs2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han autors amb aquest model");
                        System.out.println("****************************************");
                    }
                    break;
                case '3':
                    System.out.println("--------------------------------");
                    System.out.print("Portatils amb Model: ");
                    sc.nextLine();
                    String marca = sc.nextLine();
                    Pattern pattern1 = Pattern.compile(marca, Pattern.CASE_INSENSITIVE);
                    Document marcas = collection.find(regex("marca", pattern1)).first();
                    if(marcas!=null){
                        Set<String> nomcampos = marcas.keySet();
                        for (String campo : nomcampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> marcas2 = collection.find(regex("marca", pattern1));
                        for (Document doc : marcas2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han autors amb aquest model");
                        System.out.println("****************************************");
                    }
                    break;
                case '4':
                   System.out.println("--------------------------------");
                    System.out.print("Portatils amb Model: ");
                    sc.nextLine();
                    String model = sc.nextLine();
                    Pattern pattern2 = Pattern.compile(model, Pattern.CASE_INSENSITIVE);
                    Document models = collection.find(regex("model", pattern2)).first();
                    if(models!=null){
                        Set<String> nomcampos = models.keySet();
                        for (String campo : nomcampos) {
                            System.out.printf("%-20s",campo);
                        }
                        System.out.println("");
                        System.out.println("************************************************************************************************************");
                        FindIterable<Document> models2 = collection.find(regex("model", pattern2));
                        for (Document doc : models2) {
                            for (Object value : doc.values()) {
                                System.out.printf("%-20s",value);
                            }
                            System.out.println();
                        }
                    }else{
                        System.out.println("****************************************");
                        System.out.println("No hi han autors amb aquest model");
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
    
    public static void visualitzarAlumnes(){
        boolean sortir = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("**********VISUALITZAR ALUMNES**********");
            System.out.println("1. Visualitzar tots els alumnes");
            System.out.println("2. Visualitzar llibres per nom");
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
                    System.out.print("Alumnes amb Nom: ");
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
                        System.out.println("No hi han alumnes amb aquesta nom");
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
