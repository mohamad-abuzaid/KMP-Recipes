package model

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource


/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: DrawableResource,
    val bgImage: DrawableResource? = null,
    val bgImageLarge: DrawableResource? = null,
    val bgColor: Color
)

