package EverGrowth.com.EverGrowthserver.helper;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class DataGenerationHelper {

  //Información usuarios
  //Nombres
  private static final String[] aNames = {
      "Alejandro", "Sofía", "Mateo", "Valentina", "Javier", "Emma", "Pablo", "Isabella", "Diego", "Lucía",
      "Adrián", "Camila", "Carlos", "Valeria", "Martín", "Gabriela", "Julián", "María", "Tomás", "Elena",
      "Luis", "Ana", "Andrés", "Catalina", "Juan", "Laura", "Santiago", "Clara", "Emilio", "Paula"
  };
//Apellidos
  private static final String[] aSurnames = {
      "García", "Rodríguez", "López", "Martínez", "Hernández", "Pérez", "González", "Sánchez", "Ramírez", "Torres",
      "Flores", "Díaz", "Gómez", "Vázquez", "Rojas", "Ruiz", "Serrano", "Blanco", "Molina", "Suárez",
      "Castillo", "Jiménez", "Ortega", "Delgado", "Cruz", "Ramos", "Alvarez", "Morales", "Santos", "Reyes"
  };
  public static int getRandomInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }
  public static String getRandomName() {
    return aNames[(int) (Math.random() * aNames.length)];
  }

  public static String getRandomSurname() {
    return aSurnames[(int) (Math.random() * aSurnames.length)];
  }

  public static String doNormalizeString(String cadena) {
    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
    String cadenaSinAcentos = cadena;
    for (int i = 0; i < original.length(); i++) {
      cadenaSinAcentos = cadenaSinAcentos.replace(original.charAt(i), ascii.charAt(i));
    }
    return cadenaSinAcentos;
  }

//Dirección
  private static final String[] streetNames = {
      "Avenida de la Constitución", "Calle del Arenal", "Carrera de San Jerónimo", "Paseo de Recoletos",
      "Calle Fuencarral", "Avenida Gran Vía de Colón", "Paseo de la Independencia", "Calle del Príncipe",
      "Paseo de la Explanada de España", "Calle de la Princesa", "Avenida Menéndez Pelayo", "Calle de la Paz",
      "Paseo de la Alameda", "Avenida de la Libertad", "Calle del Marqués de Larios", "Calle del Prado",
      "Paseo de la Habana", "Avenida de la Victoria", "Calle Real", "Paseo de la Estación"

  };

  private static final String[] cities = {
      "Álava", "Albacete", "Alicante", "Almería", "Ávila", "Badajoz", "Islas Baleares", "Barcelona", "Burgos",
      "Cáceres", "Cádiz", "Castellón", "Ciudad Real", "Córdoba", "Coruña", "Cuenca", "Girona", "Granada", "Guadalajara",
      "Gipuzkoa", "Huelva", "Huesca", "Jaén", "León", "Lleida", "La Rioja", "Lugo", "Madrid", "Málaga", "Murcia",
      "Navarra",
      "Ourense", "Asturias", "Palencia", "Las Palmas", "Pontevedra", "Salamanca", "S.C.Tenerife", "Cantabria",
      "Segovia",
      "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Bizkaia", "Zamora", "Zaragoza",
      "Ceuta",
      "Melilla"
  };

  private static final String[] postalCodes = {
      "01001", "02001", "03001", "04001", "05001", "06001", "07001", "08001", "09001", "10001", "11001", "12001",
      "13001", "14001", "15001", "16001", "17001", "18001", "19001", "20001", "21001", "22001", "23001", "24001",
      "25001",
      "26001", "27001", "28001", "29001", "30001", "31001", "32001", "33001", "34001", "35001", "36001", "37001",
      "38001",
      "39001", "40001", "41001", "42001", "43001", "44001", "45001", "46001", "47001", "48001", "49001", "50001",
      "51001",
      "52001"
  };

  public static String generateRandomAddress() {
    Random rand = new Random();
    String street = streetNames[rand.nextInt(streetNames.length)];
    String city = cities[rand.nextInt(cities.length)];
    String postalCode = getPostalCodeForCity(city);

    return street + "," + city + "," + postalCode;
  }

  private static String getPostalCodeForCity(String city) {
    String[] cityArray = cities;
    String[] postalCodeArray = postalCodes;

    for (int i = 0; i < cityArray.length; i++) {
      if (cityArray[i].equals(city)) {
        return postalCodeArray[i];
      }
    }
    return "00000";
  }

 

  //Fechas
  public static LocalDateTime getRadomDate() {
    long minDay = LocalDate.of(2024, 5, 1).toEpochDay();
    long maxDay = LocalDate.of(2024, 6, 30).toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
    return LocalDate.ofEpochDay(randomDay).atTime(getRandomInt(0, 23), getRandomInt(0, 59), getRandomInt(0, 59));

  }

  //Stock y precio
  private static final Random random = new Random();

  public static int generateRandomStock() {
    return random.nextInt(100);
  }

  public static float generateRandomPrecio() {
    float min = 1.50f;
    float max = 7.00f;
    float randomNumber = min + random.nextFloat() * (max - min);
    randomNumber = Math.round(randomNumber * 100.0f) / 100.0f;
    
    return randomNumber;
}
  
//Productos

private static final String[] NOMBRES_PRODUCTOS = {
  "Manzana", "Naranja", "Plátano", "Pera", "Uva", "Fresa", "Sandía", "Melón", "Kiwi", "Papaya"
};

public static String generateRandomNombre() {
  Random random = new Random();
  int index = random.nextInt(NOMBRES_PRODUCTOS.length);
  return NOMBRES_PRODUCTOS[index];
}



//Categorias
  private static final String[] nombresCategoria = {
      "Carnes", "Lácteos", "Frutas y Verduras", "Bebidas", "Congelados", "Enlatados", "Cereales", "Pescado y Marisco",
      "Panadería", "Repostería",
      "Arroz", "Legumbres", "Pasta", "Conservas"
  };

  public static String getRandomCategoria() {
    return nombresCategoria[(int) (Math.random() * nombresCategoria.length)];
  }

  // Discursos 
private static String[] articulos = { "el", "la", "los", "las", "un", "una", "unos", "unas" };
private static String[] sustantivos = { "pan", "leche", "manzana", "arroz", "pollo", "pescado", "ensalada", "queso", "huevo", "naranja", 
"agua", "tomate", "carne", "yogur", "sopa", "galleta", "patata", "pizza" };
private static String[] verbos = { "come", "bebe", "prepara", "cocina", "disfruta", "compra", "vende", "elige", "prueba", "saborea" };
private static String[] adverbios = { "rápidamente", "cuidadosamente", "fácilmente", "alegremente", "con gusto" };
private static String[] conjunciones = { "y", "o", "pero", "sin embargo", "además", "por lo tanto", "así que" };
private static String[] conjuncionesSubordinadas = { "aunque", "porque", "mientras", "si", "cuando", "como", "después de", 
"antes de", "desde", "hasta", "siempre que", "donde", "dondequiera que", "si", "aun cuando", "a pesar de que", "una vez que", 
"siempre y cuando", "para que", "de manera que", "que", "a menos que", "hasta que", "cuando", "siempre que", "donde", 
"dondequiera que", "si", "aun cuando", "a pesar de que" };


public static String generateOracion() {
  int tipoOracion = random.nextInt(3);
  if (tipoOracion == 0) {
      return generarOracionSimple();
  } else if (tipoOracion == 1) {
      return generarOracionCompuesta();
  } else {
      return generarOracionCompleja();
  }
}

public static String generarOracionSimple() {
  String sujeto = generarFraseSustantivo();
  String verbo = generarFraseVerbo();
  return sujeto + " " + verbo + ".";
}

public static String generarOracionCompuesta() {
  String oracionSimple1 = generarOracionSimple();
  String conjuncion = conjunciones[random.nextInt(conjunciones.length)];
  String oracionSimple2 = generarOracionSimple();
  return oracionSimple1 + " " + conjuncion + " " + oracionSimple2;
}

public static String generarOracionCompleja() {
  String conjuncionSubordinada = conjuncionesSubordinadas[random.nextInt(conjuncionesSubordinadas.length)];
  String clausulaSubordinada = conjuncionSubordinada + " " + generarOracionSimple();
  String clausulaPrincipal = generarClausulaPrincipal();
  return clausulaSubordinada + ", " + clausulaPrincipal;
}

public static String generarClausulaPrincipal() {
    if (random.nextBoolean()) {
        return generarOracionSimple();
    } else {
        return generarOracionCompuesta();
    }
}

public static String generarFraseSustantivo() {
    String articulo = articulos[random.nextInt(articulos.length)];
    String sustantivo = sustantivos[random.nextInt(sustantivos.length)];
    return articulo + " " + sustantivo;
}

public static String generarFraseVerbo() {
    String verbo = verbos[random.nextInt(verbos.length)];
    if (random.nextBoolean()) {
        String adverbio = adverbios[random.nextInt(adverbios.length)];
        return verbo + " " + adverbio;
    } else {
        return verbo;
    }
}

public static String getSpeech(int amount) {
    StringBuilder sentences = new StringBuilder();
    for (int i = 0; i < amount; i++) {
        String sentence = generateOracion();
        sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1) ;
        sentences.append(sentence); // Agrega la oración al StringBuilder
    }
    return sentences.toString();
}

  //Telefonos
  public static String generateRandomPhone() {
    return generateRandomNumber(600000000, 699999999);
  }

  private static String generateRandomNumber(int min, int max) {
    Random random = new Random();
    int randomNumber = random.nextInt(max - min + 1) + min;
    return String.format("%09d", randomNumber);
  }


}
