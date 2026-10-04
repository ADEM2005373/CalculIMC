package com.example.calculimc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des vues par leurs identifiants
        val editTextPoids = findViewById<EditText>(R.id.editTextPoids)
        val editTextTaille = findViewById<EditText>(R.id.editTextTaille)
        val buttonCalculer = findViewById<Button>(R.id.buttonCalculer)
        val buttonEffacer = findViewById<Button>(R.id.buttonEffacer)
        val textViewImc = findViewById<TextView>(R.id.textViewImc)
        val textViewCategorie = findViewById<TextView>(R.id.textViewCategorie)

        // Action du bouton Calculer
        buttonCalculer.setOnClickListener {
            val poidsStr = editTextPoids.text.toString().trim()
            val tailleStr = editTextTaille.text.toString().trim()

            // 1. Vérification si les champs sont vides
            if (poidsStr.isEmpty() || tailleStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Conversion en nombres décimaux
            val poids = poidsStr.toDoubleOrNull()
            val taille = tailleStr.toDoubleOrNull()

            if (poids == null || taille == null) {
                Toast.makeText(this, "Veuillez entrer des valeurs valides", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Vérification que le poids et la taille sont strictement positifs
            if (poids <= 0 || taille <= 0) {
                Toast.makeText(this, "Le poids et la taille doivent être supérieurs à 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. Calcul de l'IMC
            val imc = poids / (taille * taille)
            val imcArrondi = String.format(Locale.US, "%.2f", imc).toDouble()

            // 5. Détermination de la catégorie et de la couleur
            val categorie: String
            val couleur: Int

            when {
                imc < 18.5 -> {
                    categorie = "Insuffisance pondérale"
                    couleur = Color.parseColor("#FFA500") // Orange
                }
                imc < 25.0 -> {
                    categorie = "Corpulence normale"
                    couleur = Color.parseColor("#008000") // Vert
                }
                imc < 30.0 -> {
                    categorie = "Surpoids"
                    couleur = Color.parseColor("#FFA500") // Orange
                }
                imc < 35.0 -> {
                    categorie = "Obésité modérée"
                    couleur = Color.parseColor("#FF0000") // Rouge
                }
                imc < 40.0 -> {
                    categorie = "Obésité sévère"
                    couleur = Color.parseColor("#FF0000") // Rouge
                }
                else -> {
                    categorie = "Obésité morbide"
                    couleur = Color.parseColor("#8B0000") // Rouge foncé
                }
            }

            // 6. Affichage des résultats
            textViewImc.text = "Votre IMC : $imcArrondi"
            textViewCategorie.text = "Catégorie : $categorie"
            textViewCategorie.setTextColor(couleur)
        }

        // Action du bouton Effacer
        buttonEffacer.setOnClickListener {
            editTextPoids.text.clear()
            editTextTaille.text.clear()
            textViewImc.text = ""
            textViewCategorie.text = ""
            editTextPoids.requestFocus() // Replace le curseur dans le champ du poids
        }
    }
}