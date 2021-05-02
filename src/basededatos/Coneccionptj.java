package basededatos;
import java.awt.HeadlessException;
import java.sql.*;
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
//tring sql = "DELETE FROM alergia Where idpaciente='" + codigo + "'";
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
tabla.addColumn("alergia");
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
    
}
