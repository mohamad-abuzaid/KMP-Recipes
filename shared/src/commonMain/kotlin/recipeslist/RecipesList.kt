package recipeslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import model.Recipe
import sugar

/**
 * Created by "Mohamad Abuzaid" on 25/05/2023.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun RecipesListScreen(
    items: List<Recipe>, width: Int,
    updateIds: String,
    onClick: (recipe: Recipe) -> Unit,
    isLarge: Boolean,
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().background(sugar),
        state = listState,
        columns = GridCells.Fixed(if (isLarge) 3 else 1)
    ) {
        items(items.size) { item ->
            RecipeListItemWrapper(
                scrollDirection = listState.isScrollingUp(),
                child = {
                    RecipeListItem(
                        recipe = items[item],
                        width = width,
                        onClick = onClick,
                        updateIds = updateIds
                    )
                },
            )
        }
    }
}

@Composable
private fun LazyGridState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}