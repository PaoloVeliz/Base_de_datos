package basededatos;
import java.awt.HeadlessException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Coneccionptj {
    Connection conex;
    
public void Conectarabase(){
String BD = "jdbc:postgresql://localhost:5432/odonto";
String usuario = "postgres";
String contra = "pgvsacrcjmrv19";
try{
Connection conectar;
    conectar = DriverManager.getConnection(BD, usuario, contra);
JOptionPane.showMessageDialog(null,"Base de datos conectada con exito.");
conex = conectar;
} catch (HeadlessException | SQLException e) {
JOptionPane.showMessageDialog(null, "Error al conectar " + e);
}
    }
//TODOS LOS METODOS DE LA TABLAS PACIENTE Y ALERGIA
public void eliminarpaciente(int codigo){
   try{  
//String sql = "delete from paciente where id = '" + codigo+"'";
   Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "delete from paciente where id = '" + codigo+"'";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
eliminaralergias(codigo);
JOptionPane.showMessageDialog(null, "datos eliminados con exito");
}catch(Exception e){
JOptionPane.showMessageDialog(null, e + " error al eliminar");
}
}
public void eliminaralergias(int codigo){
try{
Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "DELETE FROM alergia Where idpaciente='" + codigo + "'";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
JOptionPane.showMessageDialog(null, "datos eliminados con exito");
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}

}
public void modificarpaciente(String nombre,String apellidos,int edad,String telefono,int codigo,String escolaridad){
try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "UPDATE paciente set nombre='" + nombre + "', apellidos='" + apellidos + "', escolaridad='" + escolaridad + "', id='" + codigo + "', edad='" + edad + "' Where id='" + codigo + "'";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records updated successfully");
}
public void insertaralergias(String alergias, int codigopaciente){
 int cod = 0;
    try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
 String frase = "";
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         for(int v = 0; v < alergias.length();v++){
         char a = alergias.charAt(v);
            if(a == '/'){
          String sql = "INSERT INTO alergia (idalergia,descripcion,idpaciente) "
               + "values('" + cod  + "','" + frase + "','" + codigopaciente + "')";
         stmt.executeUpdate(sql);
         frase = "";
            }else{
         frase = frase + a;
            }
         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }


public void insertarpaciente(String nombre,String apellidos,int edad,String telefono,int codigo,String escolaridad,String alergias){
try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "INSERT INTO paciente (nombre,apellidos,telefono,escolaridad,id,edad) "
               + "values('" + nombre + "','" + apellidos + "','" + telefono + "','" + escolaridad + "','" + codigo + "','" + edad + "')";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
         insertaralergias(alergias,codigo);
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }

public DefaultTableModel presentarpacientes(){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("codigo");
tabla.addColumn("nombre");
tabla.addColumn("apellidos");
tabla.addColumn("telefono");
tabla.addColumn("edad");
tabla.addColumn("escolaridad");
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from paciente");
while(rs.next()){
datos[0] = rs.getString("id");
datos[1] = rs.getString("nombre");
datos[2] = rs.getString("apellidos");
datos[3] = rs.getString("telefono");
datos[4] = rs.getString("edad");
datos[5] = rs.getString("escolaridad");
tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "Mostrando datos de todos los pacientes");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}
public DefaultTableModel presentaralergias(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("alergia");
String datos[] = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from alergia where idpaciente='"+codigo+"'");
while(rs.next()){
datos[0] = rs.getString("id");

tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "alergias del paciente");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}

//METODOS PARA LAS CLASES DE CONSULTA Y PADECIMIENTOS Y TRATAMIENTOS
public void eliminarpacente(int codigo){
Connection c = null;
Conectarabase();
 Statement stmt = null;
c = conex;
try{
Class.forName("org.postgresql.Driver");
stmt = c.createStatement();
String sql = "DELETE FROM paciente Where id='" + codigo + "'";
ResultSet result = stmt.executeQuery(sql);

c.close();
stmt.close();
//eliminaralergias(codigo);
JOptionPane.showMessageDialog(null, "datos eliminados con exito");
}catch(Exception e){
JOptionPane.showMessageDialog(null, e + " error al eliminar");
}
}
public void eliminaralerias(int codigo){
Connection c = null;
Conectarabase();
 Statement stmt = null;
c = conex;
try{
Class.forName("org.postgresql.Driver");
stmt = c.createStatement();
String sql = "DELETE * FROM alergia Where idpaciente='" + codigo + "'";
ResultSet result = stmt.executeQuery(sql);
c.close();
stmt.close();
JOptionPane.showMessageDialog(null, "datos eliminados con exito");
}catch(Exception e){
JOptionPane.showMessageDialog(null, "no se han eliminado");
}

}
public void modificarpacinte(String nombre,String apellidos,int edad,String telefono,int codigo,String escolaridad){
try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "UPDATE paciente set nombre='" + nombre + "', apellidos='" + apellidos + "', escolaridad='" + escolaridad + "', id='" + codigo + "', edad='" + edad + "' Where id='" + codigo + "'";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records updated successfully");
}
public void insertaralergis(String alergias, int codigopaciente){
 int cod = 0;
    try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
 String frase = "";
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         for(int v = 0; v < alergias.length();v++){
         char a = alergias.charAt(v);
            if(a == '/'){
          String sql = "INSERT INTO alergia (idalergia,descripcion,idpaciente) "
               + "values('" + cod  + "','" + frase + "','" + codigopaciente + "')";
         stmt.executeUpdate(sql);
         frase = "";
            }else{
         frase = frase + a;
            }
         }
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }


public void insertarpacient(String nombre,String apellidos,int edad,String telefono,int codigo,String escolaridad,String alergias){
try {
    Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "INSERT INTO paciente (nombre,apellidos,telefono,escolaridad,id,edad) "
               + "values('" + nombre + "','" + apellidos + "','" + telefono + "','" + escolaridad + "','" + codigo + "','" + edad + "')";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
         insertaralergias(alergias,codigo);
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }

public DefaultTableModel presentarpaciente(){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("codigo");
tabla.addColumn("nombre");
tabla.addColumn("apellidos");
tabla.addColumn("telefono");
tabla.addColumn("edad");
tabla.addColumn("escolaridad");
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from paciente");
while(rs.next()){
datos[0] = rs.getString("id");
datos[1] = rs.getString("nombre");
datos[2] = rs.getString("apellidos");
datos[3] = rs.getString("telefono");
datos[4] = rs.getString("edad");
datos[5] = rs.getString("escolaridad");
tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "Mostrando datos de todos los pacientes");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}
public DefaultTableModel resentaralergias(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("alergia");
String datos[] = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from alergia where idpaciente='"+codigo+"'");
while(rs.next()){
datos[0] = rs.getString("id");

tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "alergias del paciente");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}
public DefaultTableModel mostrarpagos(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("nombrecliente");
tabla.addColumn("pago");
//<--------------------------cuantas columnas necesitas
String datos[] = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from pago where idpaciente='"+codigo+"'");
while(rs.next()){
datos[0] = rs.getString("id");

tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "alergias del paciente");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}
public String[] mostrarnombre(int codigo){
String nombre = "";
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
String sql ="select * from paciente where id = '" + codigo + "'";
ResultSet rs = stmt.executeQuery(sql);
if(rs.next()){
datos[0] = rs.getString("id");
datos[1] = rs.getString("nombre");
datos[2] = rs.getString("apellidos");
datos[3] = rs.getString("telefono");
datos[4] = rs.getString("edad");
datos[5] = rs.getString("escolaridad");
}
JOptionPane.showMessageDialog(null, "alergias del paciente");
rs.close();
stmt.close();
//c.commit();
c.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return datos;

}
//METODOS PARA LAS CLASES DE Enfermedad

    public void eliminarenfermedad(int id){
    Connection c = null;
    Conectarabase();
    Statement stmt = null;
    c = conex;
    try{
        Class.forName("org.postgresql.Driver");
        stmt = c.createStatement();
        String sql = "DELETE FROM enfermedad Where idenfermedad='" + id + "'";
        ResultSet result = stmt.executeQuery(sql);
        c.close();
        stmt.close();
        eliminaralergias(id);
        JOptionPane.showMessageDialog(null, "datos eliminados con exito");
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }

    public void modificarenfermedad(int id, String enfermedad, String descripcion){
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "UPDATE enfermedad set idenfermedad='" + id + "', nombreenfermedad='" + enfermedad + "', descripcion='" + descripcion + "' Where idenfermedad='" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records updated successfully");
    }

    public void insertarenfermedad(int id, String enfermedad, String descripcion){
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO enfermedad (idenfermedad,nombreenfermedad,descripcion) "
                  + "values('" + id + "','" + enfermedad + "','" + descripcion + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    
    public DefaultTableModel Enfermedad(){
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Id");
        tabla.addColumn("Enfermedad");
        tabla.addColumn("Descripcion");
        String datos[] = new String[6];
        Connection c = null;
        Statement stmt = null;
        try{
            Conectarabase();
            c = conex;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from enfermedad");
            while(rs.next()){
                datos[0] = rs.getString("idenfermedad");
                datos[1] = rs.getString("nombreenfermedad");
                datos[2] = rs.getString("descripcion");
                tabla.addRow(datos);
            }
            JOptionPane.showMessageDialog(null, "Mostrando datos de todas las enfermedades");
            rs.close();
            stmt.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return tabla;
    }
    public String[] mostrarenfermedades(int codigo){
    String nombre = "";
    String datos[];
        datos = new String[2];
    Connection c = null;
    Statement stmt = null;
    try{
    Conectarabase();
    c=conex;
    stmt = c.createStatement();
    String sql = "select * from enfermedad where idenfermedad = '" + codigo + "'";
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
    datos[0] = rs.getString("idenfermedad");
    datos[1] = rs.getString("nombreenfermedad");
    datos[2] = rs.getString("descripcion");
    JOptionPane.showMessageDialog(null,"ID enfermedad");
    rs.close();
    stmt.close();
    }
    }catch (Exception e){
    JOptionPane.showMessageDialog(null,e);
    }    
    return datos;
}

    //METODOS PARA LAS CLASES DE Tratamiento
    
    public void eliminarTratamiento(int id){
    Connection c = null;
    Conectarabase();
    Statement stmt = null;
    c = conex;
    try{
        Class.forName("org.postgresql.Driver");
        stmt = c.createStatement();
        String sql = "DELETE FROM tratamiento Where idtratamiento='" + id + "'";
        ResultSet result = stmt.executeQuery(sql);
        c.close();
        stmt.close();
        eliminaralergias(id);
        JOptionPane.showMessageDialog(null, "datos eliminados con exito");
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }

    public void modificarTratamiento(int id, String tratamiento, String costo){
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "UPDATE tratamiento set idtratamiento='" + id + "', nombretratamiento='" + tratamiento + "', costotratamiento='" + costo + "' Where idtratamiento='" + id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records updated successfully");
    }

    public void insertarTratamiento(int id, String tratamiento, String costo){
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO tratamiento (idtratamiento,nombretratamiento,costotratamiento) "
                  + "values('" + id + "','" + tratamiento + "','" + costo + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    
    public DefaultTableModel Tratamiento(){
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Id");
        tabla.addColumn("Tratamiento");
        tabla.addColumn("Costo (Q)");
        String datos[] = new String[6];
        Connection c = null;
        Statement stmt = null;
        try{
            Conectarabase();
            c = conex;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from tratamiento");
            while(rs.next()){
                datos[0] = rs.getString("idtratamiento");
                datos[1] = rs.getString("nombretratamiento");
                datos[2] = rs.getString("costotratamiento");
                tabla.addRow(datos);
            }
            JOptionPane.showMessageDialog(null, "Mostrando datos de todos los tratamientos");
            rs.close();
            stmt.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return tabla;
    }
    //METODOS PARA INSERTAR EN LA TABLA DE HISTORIAL ODONTOLOGICO
    public void insertarhistorial(int idpaciente, int idhistorial){
     try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO historiaodontologica (idhistoriaodnotologica,idpaciente) "
                  + "values('" + idhistorial + "','" + idpaciente + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    public void insetarpadecimiento(int idpadecimiento,int idenfermedad,String nombrepadecimiento, String descripcion,int idpaciente,int idconsulta){
     try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO padecimiento (idpadecimiento,idenfermedad,nombrepadecimiento,descripcion,idpaciente,idconsulta) "
                  + "values('" + idpadecimiento + "','" + idenfermedad + "','" + nombrepadecimiento + "','" + descripcion + "','" + idpaciente + "','" + idconsulta + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    
    
    
    }
    public DefaultTableModel mostrarhistorial(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("codigo");
tabla.addColumn("nombre");
tabla.addColumn("desctipcion");
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from padecimiento where idpaciente = '" + codigo + "'");
while(rs.next()){
datos[0] = rs.getString("idpadecimiento");
datos[1] = rs.getString("nombrepadecimiento");
datos[2] = rs.getString("descripcion");
tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "Mostrando datos de todos los pacientes");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
    }
//------------------------------------------------------------AREA PARA LOS METODOS Y FUNCIONES DEL USUARIO--------------------->
public boolean Ingreso_a_base(String usu, String Password){
boolean ingreso = false;
 //metodo para ir a buscar
try{
String datos;
Connection c = null;
Statement stmt = null;
ResultSet rs;
//CONECTAR A LA BASE
Conectarabase();
c = conex;
stmt = c.createStatement();
rs = stmt.executeQuery("select password from usuario where nombre = '" + usu + "'");
if (rs.next()){
        datos = rs.getString("password");
        if(datos.equals(Password)){
        ingreso = true;
        }
}

rs.close();
stmt.close();
//c.commit();
c.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
 //retorno de variable
    return ingreso;    
}  
//-----------------------------------METODOS NUEVOS PARA ODONTOGRAMA---------------------->
public String[] buscar_enfermedad(int codigo){
    String datos[];
    datos = new String[2];
    Connection c = null;
    Statement stmt = null;
    try{
    Conectarabase();
    c=conex;
    stmt = c.createStatement();
    String sql = "select * from enfermedad where idenfermedad = '" + codigo + "'";
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
    datos[0] = rs.getString("idenfermedad");
    datos[1] = rs.getString("nombreenfermedad");
    datos[2] = rs.getString("descripcion");
    rs.close();
    stmt.close();
    }
    }catch (Exception e){
    JOptionPane.showMessageDialog(null,e);
    }    
    return datos;
}
public void Insertar_en_odontograma(int id_odontograma,int id_paciente,String nombre_paciente,int id_historial){
int id_historialodontologico = buscar_historial(id_paciente);
if(id_historialodontologico == -1){
//<---------------------------------NO EXISTE EL HISTORIAL
JOptionPane.showMessageDialog(null,"existe el historial odontologico");
}else{
    int verificarodon = buscar_historial(id_paciente);
    if(verificarodon == -1){
    JOptionPane.showMessageDialog(null,"no existe historial");
    }else{
 try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO odontograma (idodontograma,idpaciente,nombrepaciente,idhistorialodontologico) "
                  + "values('" + id_odontograma+ "','" + id_paciente + "','" + nombre_paciente + "','" + id_historial + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    
    }
}
}
public void Insertar_diente(int iddiente,String nombrediente, int idenfermedad,int idtratamiento,int idodontograma){
 try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO diente (iddiente,nombrediente,idenfermedad,idtratamiento,idodontograma) "
                  + "values('" + iddiente+ "','" + nombrediente + "','" + idenfermedad + "','" + idtratamiento + "','" + idodontograma + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    
    
}
public int buscar_historial(int id_paciente){
int codigo = -1;
  int datos[];
    datos = new int[2];
    Connection c = null;
    Statement stmt = null;
    try{
    Conectarabase();
    c=conex;
    stmt = c.createStatement();
    String sql = "select * from historiaodontologica where idpaciente = '" + id_paciente + "'";
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
    //datos[0] = rs.getString("idhistoriaodontologica");
    datos[0] = rs.getInt("idhistoriaodnotologica");
    rs.close();
    stmt.close();
    }else{
    datos[0] = -1;
    }
    codigo = datos[0];
    }catch (Exception e){
    JOptionPane.showMessageDialog(null,e);
    } 
return codigo;
}
public int Buscar_odonto(int id_paciente){
int codigo = -1;
  int datos[];
    datos = new int[2];
    Connection c = null;
    Statement stmt = null;
    try{
    Conectarabase();
    c=conex;
    stmt = c.createStatement();
    String sql = "select * from odontograma where idpaciente = '" + id_paciente + "'";
    ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
    //datos[0] = rs.getString("idhistoriaodontologica");
    datos[0] = rs.getInt("idodontograma");
    rs.close();
    stmt.close();
    }else{
    datos[0] = -1;
    }
    codigo = datos[0];
    }catch (Exception e){
    JOptionPane.showMessageDialog(null,e);
    } 
return codigo;
}

public DefaultTableModel mostrarodonto(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("nombre");
tabla.addColumn("id de enfermedad");
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
int buscar = Buscar_odonto(codigo);
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
ResultSet rs = stmt.executeQuery("Select * from diente where idodontograma = '" + buscar + "'");
while(rs.next()){
datos[0] = rs.getString("nombrediente");
datos[1] = rs.getString("idenfermedad");
tabla.addRow(datos);
}
JOptionPane.showMessageDialog(null, "Mostrando datos de todos los pacientes");
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
    }
//<----------------------------------ESPACIO PARA CITAS
 public void insertarcita(int idconsulta, java.sql.Date fecha, int idpaciente, String nombre, String apellidos, String razon){
     try {
            Class.forName("org.postgresql.Driver");
            Connection c = null;
            Statement stmt = null;
            Conectarabase();
            c = conex;
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "INSERT INTO consulta (idconsulta,fechaconsulta, idpaciente, nombre, apellidos, razon) "
                  + "values('" + idconsulta + "', '" + fecha + "', '" + idpaciente + "','" + nombre + "','" + apellidos + "','" + razon + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
   
    public String[] mostrarTratamiento(int codigo){
    String nombre = "";
    String datos[];
        datos = new String[3];
        Connection c = null;
        Statement stmt = null;
    try{
        Conectarabase();
        c=conex;
        stmt = c.createStatement();
        String sql = "select * from tratamiento where idtratamiento = '" + codigo + "'";
        ResultSet rs = stmt.executeQuery(sql);
    if(rs.next()){
        datos[0] = rs.getString("idtratamiento");
        datos[1] = rs.getString("nombretratamiento");
        datos[2] = rs.getString("costotratamiento");
        rs.close();
        stmt.close();
    }
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
    }    
    return datos;
}
        public DefaultTableModel mostrarCITAS(java.sql.Date fechainicio,java.sql.Date fechafin, int opcion){
          DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("idconsulta");
        tabla.addColumn("fechaconsulta");
        tabla.addColumn("idpaciente");
        tabla.addColumn("nombre");
        tabla.addColumn("apellido");
        tabla.addColumn("razon");
        String datos[];
        datos = new String[6];
        Connection c = null;
        Statement stmt = null;
    try{
        Conectarabase();
        c=conex;
        stmt = c.createStatement();
        String sql = null;
        if(opcion==1){
             sql = "select * from consulta where fechaconsulta = '" + fechainicio + "'";
        }
        if(opcion==2){
             sql = "select * from consulta where fechaconsulta beetween '" + fechainicio + "' and '" + fechafin + "'" ;
        }
        if(opcion==3){
             sql = "select * from consulta where MONTH(fechaconsulta) = '" + (fechainicio.getMonth()+1) + "'";
        }
        ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()){
        datos[0] = rs.getString("idconsulta");
        datos[1] = rs.getString("fechaconsulta");
        datos[2] = rs.getString("idpaciente");
        datos[3] = rs.getString("nombre");
        datos[4] = rs.getString("apellidos");
        datos[5] = rs.getString("razon");
        tabla.addRow(datos);
    }
    JOptionPane.showMessageDialog(null,"fecha");
        rs.close();
        stmt.close();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
    }    
    return tabla;
    
}
 //<--------------------------------------------------------PARTE DE PAGOS 
     public String BuscarPaciente(int codigo){
    String nombre="";
    Connection c = null;
    Conectarabase();
    Statement stmt = null;
    c = conex;
    try{
        Class.forName("org.postgresql.Driver");
        stmt = c.createStatement();
        String sql = "SELECT FROM paciente Where id='" + codigo + "'";
        ResultSet result = stmt.executeQuery("SELECT * FROM paciente");
        if(result.next()){
        nombre = result.getString("nombre");
        }
        c.close();
        stmt.close();
        return nombre;
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e + "No existe el codigo");
    }
    return nombre;
}

public String Padecimientos(){
    ArrayList<String> padecimiento = new ArrayList<String>();
    String datos = "";
    Connection c = null;
    Statement stmt = null;
    try{
    Conectarabase();
    c = conex;
    stmt = c.createStatement();
    String sql ="Select * from padecimiento'";
    ResultSet rs = stmt.executeQuery(sql);
    while(rs.next()){
    datos = rs.getString("nombrepadecimiento");
    padecimiento.add(datos);
    }
    rs.close();
    stmt.close();
    }catch (Exception e) {
    JOptionPane.showMessageDialog(null,e);
    }
    return padecimiento.toString();
}

public void NuevoSaldo(int idp, String nombre, int monto, String fecha, String padecimiento,String descripcion, String bool){
    int x = 0, i = 0,k=0;
    if(bool.equals("PAGADO")){
        k = 1;
    }else{
        k = 0;
    }
    try {
   Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
          String sql ="Select * from padecimiento where nombrepadecimiento='"+padecimiento+"'";
         ResultSet rs = stmt.executeQuery(sql);
         x = rs.getInt("idpadecimiento");
         String sql1 = "INSERT INTO pagoprocedimiento (idprocedimientopago,idpadecimiento,descripcion,costo,idpago,idpaciente,fechadepago,cancelado) "
               + "values('" + "" + "','" + x + "','" + descripcion + "','" + monto + "','" + "" + "','" + idp + "','" + fecha + "','" + k + "')";
         stmt.execute(sql1);
         String sql2 ="Select * from pago where idpaciente='"+idp+"'";
         ResultSet rs1 = stmt.executeQuery(sql2);
         i = rs1.getInt("saldototal");
         String sql3 = "INSERT INTO pago (idpago,fechadepago,saldopendiente,saldototal,idpaciente) "
               + "values('" + "" + "','" + fecha + "','" + monto + "','" + (i + monto) + "','" + idp + "')";
         stmt.execute(sql3);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records updated successfully");
}

public String Total(int id){
    String total = "";
    Connection c = null;
    Conectarabase();
    Statement stmt = null;
    c = conex;
    try{
        Class.forName("org.postgresql.Driver");
        stmt = c.createStatement();
        String sql ="SELECT * FROM pago WHERE idpaciente='" + id + "'";
        ResultSet result = stmt.executeQuery(sql);
        total = result.getString("saldototal");
        c.close();
        stmt.close();
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e + "No existe el codigo");
    }
    return total;
}

public void Pagorealizado(String fechapago, int monto, int codigo, int saldo){
    int x = 0, i = 0;
    try {
   Class.forName("org.postgresql.Driver");
 Connection c = null;
 Statement stmt = null;
 Conectarabase();
 c = conex;
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql ="Select costo from pagoprocedimiento where idpago='"+codigo+"'";
         ResultSet rs = stmt.executeQuery(sql);
         x = rs.getInt("costo") - monto;
         String sql1 ="Select saldototal FROM pago where idpago='" +codigo+"'";
         ResultSet rs2 = stmt.executeQuery(sql1);
         i = rs2.getInt("saldototal") - monto;
         if (x == 0){
             String sql2 = "UPDATE pagoprocedimiento set idpago='" + codigo + "', saldo='" + x + "', fechadepago='" + fechapago + "', cancelado"+ 1 +"'";
              stmt.executeUpdate(sql2);
         }else{
             String sql2 = "UPDATE pagoprocedimiento set idpago='" + codigo + "', saldo='" + x + "', fechadepago='" + fechapago + "', cancelado"+ 0 +"'";
              stmt.executeUpdate(sql2);
         }
         String sql3 = "UPDATE pago set idpago='" + codigo + "', saldopendiente='" + i + "', fechadepago='" + fechapago +"'";
         stmt.execute(sql3);
         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records updated successfully");
}

public String SaldoPendiente(int codigo){
    String pagopendiente = "";
    Connection c = null;
    Conectarabase();
    Statement stmt = null;
    c = conex;
    try{
        Class.forName("org.postgresql.Driver");
        stmt = c.createStatement();
        String sql ="SELECT * FROM pago WHERE idpaciente='" + codigo + "'";
        ResultSet result = stmt.executeQuery(sql);
        pagopendiente = result.getString("saldopendiente");
        c.close();
        stmt.close();
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e + "No existe el codigo");
    }
    return pagopendiente;
}

public DefaultTableModel proximopago(int codigo){
    DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("Id Procedimiento");
tabla.addColumn("Id Padecimiento");
tabla.addColumn("Fecha de pago");
tabla.addColumn("Cancelado");
String[] datos = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
String sql ="SELECT * FROM pagoprocedimiento WHERE idpaciente='"+codigo+"', AND  fechadepago >'" + LocalDate.now() +"'";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
datos[0] = rs.getString("idpago");
datos[1] = rs.getString("idpadecimiento");
datos[2] = rs.getString("fechadepago");
datos[3] = rs.getString("cancelado");
tabla.addRow(datos);
}
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}

public DefaultTableModel mostrarpagosrecientes(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("Id Procedimiento");
tabla.addColumn("Id Padecimiento");
tabla.addColumn("Fecha de pago");
tabla.addColumn("Cancelado");
String[] datos = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
String sql ="SELECT * FROM pagoprocedimiento WHERE idpaciente='"+codigo+"', AND  cancelado ='" + 1 +"'";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
datos[0] = rs.getString("idpago");
datos[1] = rs.getString("idpadecimiento");
datos[2] = rs.getString("fechadepago");
datos[3] = rs.getString("cancelado");
tabla.addRow(datos);
}
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}

public DefaultTableModel mostrarpagos1(int codigo){
DefaultTableModel tabla = new DefaultTableModel();
tabla.addColumn("Id Procedimiento");
tabla.addColumn("Id Padecimiento");
tabla.addColumn("Fecha de pago");
tabla.addColumn("Cancelado");
String[] datos = new String[4];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
String sql ="Select * from pagoprocedimiento where idpaciente='"+codigo+"'";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
datos[0] = rs.getString("idpago");
datos[1] = rs.getString("idpadecimiento");
datos[2] = rs.getString("fechadepago");
datos[3] = rs.getString("cancelado");
tabla.addRow(datos);
}
rs.close();
stmt.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return tabla;
}

public String[] mostrarnombre1(int codigo){
String datos[] = new String[6];
Connection c = null;
Statement stmt = null;
try{
Conectarabase();
c = conex;
stmt = c.createStatement();
String sql ="select * from paciente where id = '" + codigo + "'";
ResultSet rs = stmt.executeQuery(sql);
if(rs.next()){
datos[0] = rs.getString("id");
datos[1] = rs.getString("nombre");
datos[2] = rs.getString("apellidos");
datos[3] = rs.getString("telefono");
datos[4] = rs.getString("edad");
datos[5] = rs.getString("escolaridad");
}
JOptionPane.showMessageDialog(null, "alergias del paciente");
rs.close();
stmt.close();
//c.commit();
c.close();
}catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
}
return datos;

}
}
