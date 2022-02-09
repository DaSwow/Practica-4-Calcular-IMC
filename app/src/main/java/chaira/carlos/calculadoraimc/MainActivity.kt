package chaira.carlos.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de variables
        var lblResultado:TextView = findViewById(R.id.tvResultado)
        var lblEstado:TextView = findViewById(R.id.tvEstado)

        //Declaración editText
        var  txtAltura: TextView = findViewById(R.id.etAltura)
        var  txtPeso: TextView = findViewById(R.id.etPeso)

        //Declaración Botón
        var btnCalcular : Button = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            if(!txtAltura.text.isBlank() && !txtPeso.text.isBlank()){

                //Calcular IMC y mostrar el resultado
                val imcNum = calcularImc(txtAltura.text.toString().toDouble(),
                    txtPeso.text.toString().toDouble())
                lblResultado.setText(imcNum.toString())

                //Se obtiene y muestra el estado del usuario
                val estado = this.obtenEstado(imcNum)
                lblEstado.setText(estado)

                //Se añade un color dependiendo del resultado
                when(estado){
                    "Bajo peso" -> lblEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> lblEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> lblEstado.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad Grado 1" -> lblEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad Grado 2" -> lblEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad Grado 3" -> lblEstado.setBackgroundResource(R.color.colorRed)

                }

            }
        }
    }

    /**
     * Función que calcula el IMC en base al peso y la altura
     */
    fun calcularImc(altura:Double,peso:Double):Double{
        val imc:Double =  (peso/ (Math.pow(altura,2.0)))
        return imc
    }

    /**
     * Función que devuelve el estado del usuario en base a su IMC
     */
    fun obtenEstado(imc:Double):String{
        when{
            imc < 18.5 -> return "Bajo peso"
            imc >= 18.5 && imc<= 24.9-> return "Saludable"
            imc >= 24.9 && imc<= 29.9-> return "Sobrepeso"
            imc >= 29.9 && imc<= 34.9-> return "Obesidad Grado 1"
            imc >= 34.9 && imc<= 40-> return "Obesidad Grado 2"
            imc >= 40.0 -> return "Obesidad Grado 3"
        }
        return "Error"
    }

}
