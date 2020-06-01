//Programa para manejo de citas.
import java.util.*;
import java.io.*;
public class AgendaCitasMascota {
	
	//Variables para hacer referencia a la informacion persona
	public static String NOMBRE;
	public static String TELEFONO;
	public static String CORREO;

	//Variables hacer referencia a informacion de la cita
	public static String DIRECCION;
	public static String FECHA;
	public static String HORA;
	public static String DESCRIPCION;
	
	//Variable de la ruta del archivo.
	static File ArchivoTXT = new File ("Database.txt");
	
	public static void main(String[] args){
		comprobarBD();
		menuOpcionesPrincipal();
			
	}

	//Menu de opciones.
	public static void menuOpcionesPrincipal() {
		Scanner opmenu = new Scanner(System.in);
		int opcion;
		
		do{
			
		System.out.println("--------------------------------------------------------");
		System.out.println("                   AGENDA DE CITAS");
		System.out.println("--------------------------------------------------------\n");
			
		System.out.println("INGRESE UNA OPCION\n");
		System.out.println("1.- AGREGAR NUEVA CITA");
		System.out.println("2.- BUSCAR CITA ");
		System.out.println("3.- ACTUALIZAR CITA");
		System.out.println("4.- ELIMINAR CITA");
		System.out.println("5.- MOSTRAR TODAS LAS CITAS");
		System.out.println("6.- SALIR DEL PROGRAMA");
		opcion = opmenu.nextInt();
		System.out.println("--------------------------------------------------------\n");
		switch(opcion){
		case 1:
			//Opcion para agregar registros al archivo.
			System.out.println("--------------------------------------------------------");
			System.out.println("              Agregar Nuevo Registro");
			System.out.println("--------------------------------------------------------\n");
			agregarRegistro();
			System.out.println("--------------------------------------------------------\n");
			break;
		case 2:
			System.out.println("\n--------------------------------------------------------");
			System.out.println("                       Buscando...");
			System.out.println("--------------------------------------------------------\n");
			menuBuscarRegistro();
			System.out.println("\n--------------------------------------------------------");
			System.out.println("                    Fin de la busqueda. ");
			System.out.println("--------------------------------------------------------\n\n");
			break;
		case 3:
			System.out.println("Actualizar Datos");
			//modificarArchivo();
			break;
		case 4:			
			System.out.println("Eliminar datos");
			//eliminarRegistro();
			break;
		case 5:
			//Opcion para mostrar las citas agregadas.
			System.out.println("--------------------------------------------------------");
			System.out.println("        Total de registros dentro delarchivo");
			System.out.println("--------------------------------------------------------\n\n");
			mostrarRegistros();
			System.out.println("\n--------------------------------------------------------");
			System.out.println("    Fin del Total de registros dentro delarchivo ");
            System.out.println("--------------------------------------------------------\n");
			break;
		case 6:
			System.out.println("Salir");
			break;
		default:
			System.out.println("Opcion invalida");
			break;
		}
	}while(opcion !=6);
		
	}
	
	//Comprobar si el archivo existe.
	public static void comprobarBD(){
		try{
			if(ArchivoTXT.exists()){
				System.out.println("Puede trabajar en el archivo.\n");
			}else{ArchivoTXT.createNewFile();
				System.out.println("Se ha creado el archivo, ya apuedes trabajar\n");
			}
		}catch(Exception e){
			System.out.println("Error" + e.getMessage());
			System.out.println("No se puede trabajar en el archivo.\n");
		}
	}
	
	//Agregar Registro.
	public static void agregarRegistro(){
		Scanner scCampo = new Scanner (System.in);
		String SepCampo = "|";//Separador de campo
		int tamRegistro = 0;//Variable apra calcular tama?o del registro		
		
		try{
			
		BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ArchivoTXT,true), "utf-8"));	
			
		System.out.println("Ingrese Cliente / Nombre Macota:");
		NOMBRE = scCampo.nextLine();
		
		System.out.println("\nIngrese el telefono:");
		TELEFONO = scCampo.nextLine();
		
		System.out.println("\nIngrese el correo electronico:");
		CORREO = scCampo.nextLine();
		
		System.out.println("\nIngrese la direccion de la cita:");
		DIRECCION = scCampo.nextLine();
		
		System.out.println("\nIngrese la fecha de la cita:");
		FECHA= scCampo.nextLine();
		
		System.out.println("\nIngrese la hora de la cita:");
		HORA = scCampo.nextLine();
		
		System.out.println("\nIngrese una descripcion acerca de la  cita:");
		DESCRIPCION = scCampo.nextLine();
		
		tamRegistro = NOMBRE.length()+SepCampo.length()+TELEFONO.length()+SepCampo.length()+CORREO.length()+SepCampo.length()+
		DIRECCION.length()+SepCampo.length()+FECHA.length()+SepCampo.length()+HORA.length()+SepCampo.length()+DESCRIPCION.length()+SepCampo.length();
		
		Fescribe.write(tamRegistro+SepCampo+NOMBRE+SepCampo+TELEFONO+SepCampo+CORREO+SepCampo+DIRECCION+
				SepCampo+FECHA+SepCampo+HORA+SepCampo+DESCRIPCION+SepCampo);
		
		Fescribe.close();
		
		System.out.println("\nEl registro se guardo correctamente.\n");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
}

	//Menu con opciones para buscar.
	public static void menuBuscarRegistro(){
		Scanner scopc = new Scanner(System.in);
		int opcion=0;
		
		System.out.println("Ingrese una opcion\n");
		System.out.println("1.- Busqueda por nombre");
		System.out.println("2.- Busqueda por telefono");
		System.out.println("3.- Busqueda por correo");
		System.out.println("4.- Regresar al menu principal");
		opcion= scopc.nextInt();
		switch(opcion){
		case 1:
			buscarRegistroNombre();
			break;
		case 2:
			buscarRegistroTelefono();
			break;
		case 3:
			buscarRegistroCorreo();
			break;
		case 4:
			menuOpcionesPrincipal();
			break;
		default:
			System.out.println("Opcion invalida");
			break;
			
		}
	}
	
	//Buscar Registro por nombre.
	public static void buscarRegistroNombre(){
		Scanner sc = new Scanner (System.in);
	
		String buscar, leer;
		boolean encontrado = true;
		
		try{
			
			BufferedReader br = new BufferedReader (new FileReader(ArchivoTXT));	
			System.out.println("Ingrese el nombre: ");
			
			buscar = sc.nextLine();
			
			while((leer = br.readLine()) != null){
					
				StringTokenizer st = new StringTokenizer(leer, "|");

				
				if(leer.contains(buscar)){
					
					String nombre = null;
					
					while (nombre != buscar && encontrado ){
						String tamregistro = st.nextToken().trim();
			            nombre =  st.nextToken().trim();
			            String telefono =  st.nextToken().trim();
			            String correo =  st.nextToken().trim();
			            
			            String direccion =  st.nextToken().trim();
			            String fecha =  st.nextToken().trim();
			            String hora =  st.nextToken().trim();
			            String descripcion =  st.nextToken().trim();
			            
			        if(nombre.equals(buscar)){
			            	
				            encontrado = false;
				            
				            System.out.println("\nEl resultado de la busqueda " +"\""+ buscar + "\""+ " si existe...\n");
				            
				            System.out.println("\nEl nombre es:\t\t" + nombre);
				            System.out.println("El telefono es:\t\t" + telefono);
				            System.out.println("El correo es:\t\t" + correo);
				            
				            System.out.println("El direccion es:\t" + direccion);
				            System.out.println("El fecha es:\t\t" + fecha);
				            System.out.println("El hora es:\t\t" + hora);
				            System.out.println("El descripcion es:\t" + descripcion + "\n");
				            }
						}
				}else{System.out.println("\nEl resultado de la busqueda " +"\""+ buscar + "\""+ " no existe...");}
			}
			br.close();
			
		}catch(Exception e){
			System.out.println("Error" + e );
		}
	}

	//Buscar Registro por telefono.
	public static void buscarRegistroTelefono(){
		Scanner sc = new Scanner (System.in);
	
		String buscar, leer;
		boolean encontrado = true;
		
		try{
			
			BufferedReader br = new BufferedReader (new FileReader(ArchivoTXT));	
			System.out.println("Ingrese el telefono: ");
			buscar = sc.nextLine();
			
			while((leer = br.readLine()) != null){
					
				StringTokenizer st = new StringTokenizer(leer, "|");

				
				if(leer.contains(buscar)){
					
					String telefono = null;
					
					while (NOMBRE != buscar && encontrado ){
						String tamregistro = st.nextToken().trim();
			            String nombre =  st.nextToken().trim();
			            telefono =  st.nextToken().trim();
			            String correo =  st.nextToken().trim();
			            
			            String direccion =  st.nextToken().trim();
			            String fecha =  st.nextToken().trim();
			            String hora =  st.nextToken().trim();
			            String descripcion =  st.nextToken().trim();
			            
			        if(telefono.equals(buscar)){
			            	
				            encontrado = false;
				            	
				            System.out.println("\nEl resultado de la busqueda " +"\""+ buscar + "\""+ " si existe...\n");
				            
				            System.out.println("\nEl nombre es:\t\t" + nombre);
				            System.out.println("El telefono es:\t\t" + telefono);
				            System.out.println("El correo es:\t\t" + correo);
				            
				            System.out.println("El direccion es:\t" + direccion);
				            System.out.println("El fecha es:\t\t" + fecha);
				            System.out.println("El hora es:\t\t" + hora);
				            System.out.println("El descripcion es:\t" + descripcion + "\n");
				            }
						}
				}else{System.out.println("\nel registro no exixte");}
			}
			br.close();
			
		}catch(Exception e){
			System.out.println("Error" + e );
		}
	}

	//Buscar Registro por correo.
	public static void buscarRegistroCorreo(){
		Scanner sc = new Scanner (System.in);
	
		String buscar, leer;
		boolean encontrado = true;
		
		try{
			
			BufferedReader br = new BufferedReader (new FileReader(ArchivoTXT));	
			System.out.println("Ingrese el correo: ");
			buscar = sc.nextLine();
			
			while((leer = br.readLine()) != null){
					
				StringTokenizer st = new StringTokenizer(leer, "|");

				
				if(leer.contains(buscar)){
					
					String correo = null;
					
					while (NOMBRE != buscar && encontrado ){
						String tamregistro = st.nextToken().trim();
			            String nombre =  st.nextToken().trim();
			            String telefono =  st.nextToken().trim();
			            correo =  st.nextToken().trim();
			            
			            String direccion =  st.nextToken().trim();
			            String fecha =  st.nextToken().trim();
			            String hora =  st.nextToken().trim();
			            String descripcion =  st.nextToken().trim();
			            
			        if(correo.equals(buscar)){
			            	
				            encontrado = false;
				            	
				            System.out.println("\nEl resultado de la busqueda " +"\""+ buscar + "\""+ " si existe...\n");
				            
				            System.out.println("\nEl nombre es:\t\t" + nombre);
				            System.out.println("El telefono es:\t\t" + telefono);
				            System.out.println("El correo es:\t\t" + correo);
				            
				            System.out.println("El direccion es:\t" + direccion);
				            System.out.println("El fecha es:\t\t" + fecha);
				            System.out.println("El hora es:\t\t" + hora);
				            System.out.println("El descripcion es:\t" + descripcion + "\n");
				            }
						}
				}else{System.out.println("\nel registro no exixte");}
			}
			br.close();
			
		}catch(Exception e){
			System.out.println("Error" + e );
		}
	}


	//Mostrar todos los registros que existen dentro del archivo.
	public static void mostrarRegistros(){
 		
		try{
			String linea = null;
			BufferedReader leerFichero = new BufferedReader (new FileReader(ArchivoTXT));
			
			while( (linea = leerFichero.readLine()) != null){

	            StringTokenizer mistokens = new StringTokenizer(linea, "|");//StringTokenizer sirve para separar el registro por campos.

	            while(mistokens.hasMoreTokens()){
	            
	            String tamregistro = mistokens.nextToken().trim();//Trimp sirve para eliminar espacios.
	            NOMBRE =  mistokens.nextToken().trim();
	            TELEFONO =  mistokens.nextToken().trim();
	            CORREO =  mistokens.nextToken().trim();
	            
	            DIRECCION =  mistokens.nextToken().trim();
	            FECHA =  mistokens.nextToken().trim();
	            HORA =  mistokens.nextToken().trim();
	            DESCRIPCION =  mistokens.nextToken().trim();

	            System.out.println("El nombre es:\t\t" + NOMBRE);
	            System.out.println("El telefono es:\t\t" + TELEFONO);
	            System.out.println("El correo es:\t\t" + CORREO);
	            
	            System.out.println("El direccion es:\t" + DIRECCION);
	            System.out.println("El fecha es:\t\t" + FECHA);
	            System.out.println("El hora es:\t\t" + HORA);
	            System.out.println("El descripcion es:\t" + DESCRIPCION);
	            
	            System.out.println("\n");
	   
	            }
	            }
	         leerFichero.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	//Modificar Registro
	public static void modificarArchivo(){
		
	}
	
	//Eliminar Registro
	public static void eliminarRegistro(){
	
	}
	
	
}