package xyz.omniplay.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AspectRatio
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material.icons.rounded.RepeatOne
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.automirrored.rounded.VolumeOff
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Official Google Material 3 Icon & Component catalog for Omniplay.
 * Powered by androidx.compose.material3.
 */
object Material3Icons {
    val Play: ImageVector = Icons.Rounded.PlayArrow
    val Pause: ImageVector = Icons.Rounded.Pause
    val Next: ImageVector = Icons.Rounded.SkipNext
    val Previous: ImageVector = Icons.Rounded.SkipPrevious
    val Forward10: ImageVector = Icons.Rounded.Forward10
    val Replay10: ImageVector = Icons.Rounded.Replay10
    val Shuffle: ImageVector = Icons.Rounded.Shuffle
    val Repeat: ImageVector = Icons.Rounded.Repeat
    val RepeatOne: ImageVector = Icons.Rounded.RepeatOne
    val VolumeUp: ImageVector = Icons.AutoMirrored.Rounded.VolumeUp
    val VolumeOff: ImageVector = Icons.AutoMirrored.Rounded.VolumeOff
    val Brightness: ImageVector = Icons.Rounded.LightMode
    val AspectRatio: ImageVector = Icons.Rounded.AspectRatio
    val ScreenRotation: ImageVector = Icons.Rounded.ScreenRotation
    val Search: ImageVector = Icons.Rounded.Search
    val Menu: ImageVector = Icons.Rounded.Menu
    val Timer: ImageVector = Icons.Rounded.Timer
    val MusicNote: ImageVector = Icons.Rounded.MusicNote
}

@Composable
fun Material3PlayerIcon(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = if (tint == Color.Unspecified) MaterialTheme.colorScheme.primary else tint
    )
}
