<?php
    $repuesta[0] = "";

    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "finalproject";
    
   
    $transaccionAndroid = $_POST["transaccion"];
    
    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    // Check connection
    if ($conn->connect_error) {
        $repuesta[0] = $conn->connect_error;
    } 


    if($transaccionAndroid == "insertsitio"){    

        $descAndroid = $_POST["desc"];
        $dirrAndroid = $_POST["dirr"];
		$websAndroid = $_POST["webs"];
		$emaiAndroid = $_POST["emai"];
		$fijoAndroid = $_POST["fijo"];
		$celuAndroid = $_POST["celu"];
    
        $sql = "INSERT INTO sitiosturisticos VALUES (null, '$descAndroid', '$dirrAndroid', '$websAndroid','$emaiAndroid', '$fijoAndroid', '$celuAndroid' )";

        if ($conn->query($sql) === TRUE) {
            $repuesta[0] = "Registro Satisfactorio";
        } else {
            $repuesta[0] = $conn->error;
        }
	echo json_encode($repuesta);
    }else if($transaccionAndroid == "updatesitio"){    
		$codAndroid = $_POST["cod"];
        $descAndroid = $_POST["desc"];
        $dirrAndroid = $_POST["dirr"];
		$websAndroid = $_POST["webs"];
		$emaiAndroid = $_POST["emai"];
		$fijoAndroid = $_POST["fijo"];
		$celuAndroid = $_POST["celu"];
    
        $sql = "UPDATE sitiosturisticos SET descripcion='$descAndroid', direccion='$dirrAndroid', sitioweb='$websAndroid', email='$emaiAndroid', fijo = '$fijoAndroid', celular='$celuAndroid' WHERE codigo='$codAndroid'";

        if ($conn->query($sql) === TRUE) {
            $repuesta[0] = "Actualizacion Satisfactoria";
        } else {
            $repuesta[0] = $conn->error;
        }

	echo json_encode($repuesta);		
	}else if($transaccionAndroid == "deletesitio"){    
		$codAndroid = $_POST["cod"];
    
        $sql = "DELETE FROM sitiosturisticos WHERE codigo='$codAndroid'";

        if ($conn->query($sql) === TRUE) {
            $repuesta[0] = "Registro eliminado Satisfactoriamente";
        } else {
            $repuesta[0] = $conn->error;
        }

	echo json_encode($repuesta);		
	}else if($transaccionAndroid == "selectsitio"){
  
        $arregloDatos = null;
        $sql = "SELECT * FROM sitiosturisticos";
        $result = $conn->query($sql);
       
        if ($result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
                $arregloDatos[] = $row;
            }           

           // $repuesta[0] = true;
            $repuesta[0] = $arregloDatos;
        } else {
            $repuesta[0] = false;
        }
		echo json_encode($arregloDatos);
    }
	
	
	else  if($transaccionAndroid == "inserthotel"){    
		$descAndroid = $_POST["desc"];
		$websAndroid = $_POST["webs"];
        $longAndroid = $_POST["long"];
        $latAndroid = $_POST["lat"];	
		$picAndroid = $_POST["pic"];
   
        $sql = "INSERT INTO hoteles VALUES (null, '$descAndroid', '$websAndroid', '$longAndroid','$latAndroid', '$picAndroid')";

        if ($conn->query($sql) === TRUE) {
            $repuesta[0] = "Registro Satisfactorio";
        } else {
            $repuesta[0] = $conn->error;
        }
		echo json_encode($repuesta);
		
	}

$conn->close();
 
 
 /*

   // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    } 

        @$trans = $_GET["trans"];
    

        if($trans == "Insertar"){

                $id = $_GET["id"];
                $nombre = $_GET["nombre"];
                $telefono = $_GET["telefono"];

                $sql = "insert into usuarios values(null,'".$nombre."','".$telefono."')";
                $result = $conn->query($sql);
                
                echo "Inserto correctamente";

        }else{

                $id = $_POST["id"];

                $sql = "SELECT * FROM usuarios where id = '".$id."'";
                $result = $conn->query($sql);
                
                if ($result->num_rows == 1) {
                    
                    $row = $result->fetch_assoc();
                    echo $row["id"]."-".$row["nombre"]."-".$row["telefono"];
                    
                } else {
                    echo "0-No hay resultados";
                }
          
        }
*/
?>