package com.nexora.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF090E17)
private val Panel = Color(0xFF151F2D)
private val Gold = Color(0xFFFFD166)
private val Green = Color(0xFF16E6A0)
private val Soft = Color(0xFFDCE5EF)

data class BotLink(val title: String, val subtitle: String, val url: String, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    private val bots = listOf(
        BotLink("Download Earn", "Downloads & rewards", "https://t.me/Downloadearn_bot?start=ref_8011512252", Icons.Default.Download),
        BotLink("Hello Movie", "Movie bot", "https://t.me/hello_movie2_bot", Icons.Default.Movie),
        BotLink("Video Cash", "Video rewards", "https://t.me/video1_cash2_bot", Icons.Default.PlayCircle),
        BotLink("Spin Wheel", "Spin & try your luck", "https://t.me/spin_wheel00_bot", Icons.Default.Casino),
        BotLink("My Bit YouTube", "YouTube bot", "https://t.me/mybityoutubebot", Icons.Default.SmartDisplay),
        BotLink("IQ Esports", "Esports bot", "https://t.me/iqesporstbot", Icons.Default.SportsEsports),
        BotLink("Earn GoGo", "More earning activities", "https://t.me/earngogo_Bot", Icons.Default.AccountBalanceWallet)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(
                background = Night, surface = Panel, primary = Green,
                secondary = Gold, onBackground = Soft, onSurface = Soft
            )) {
                NexoraHome(bots) { link ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun NexoraHome(bots: List<BotLink>, onOpen: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF101B2A), Night, Color(0xFF071B18))))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("NEXORA", color = Gold, fontSize = 34.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                    Text("REWARDS  •  EARN  •  PLAY", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp)
                }
                Surface(color = Color(0xFF243329), shape = RoundedCornerShape(16.dp)) {
                    Text("N", modifier = Modifier.padding(horizontal = 17.dp, vertical = 8.dp),
                        color = Gold, fontSize = 27.sp, fontWeight = FontWeight.Black)
                }
            }
            Spacer(Modifier.height(22.dp))
            Surface(
                color = Color(0xFF17283A),
                shape = RoundedCornerShape(22.dp),
                tonalElevation = 4.dp
            ) {
                Column(Modifier.fillMaxWidth().padding(18.dp)) {
                    Text("YOUR ULTIMATE", color = Soft.copy(alpha = .75f), fontSize = 11.sp, letterSpacing = 2.sp)
                    Text("EARNING HUB", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(Modifier.height(5.dp))
                    Text("Choose a bot and continue on Telegram.", color = Soft.copy(alpha = .8f), fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("EXPLORE BOTS", color = Gold, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                Spacer(Modifier.weight(1f))
                Text("7 SERVICES", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(bots) { bot ->
                    BotCard(bot) { onOpen(bot.url) }
                }
            }
            Text("NEXORA  •  EARN MORE, LIVE BETTER", modifier = Modifier.fillMaxWidth(),
                color = Gold.copy(alpha = .9f), fontSize = 10.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, letterSpacing = 1.sp)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun BotCard(bot: BotLink, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(142.dp).clickable { onClick() },
        color = Panel,
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Green.copy(alpha = .45f)),
        tonalElevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Surface(color = Color(0xFF26372F), shape = RoundedCornerShape(13.dp)) {
                Icon(bot.icon, contentDescription = null, tint = Gold, modifier = Modifier.padding(10.dp).size(25.dp))
            }
            Column {
                Text(bot.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(bot.subtitle, color = Soft.copy(alpha = .65f), fontSize = 10.sp)
            }
            Text("OPEN  ↗", color = Green, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
        }
    }
}
