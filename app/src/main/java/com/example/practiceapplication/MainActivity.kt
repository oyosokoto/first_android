package com.example.practiceapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceapplication.data.BlogType
import com.example.practiceapplication.data.Person
import com.example.practiceapplication.data.RssTile
import com.example.practiceapplication.ui.theme.PracticeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var people = listOf(
            Person("Samuel", "Andrew", 26),
            Person("Samuels", "Andrew", 6),
            Person("Samuel", "Andrews", 2),
            Person("Samuella", "Andrew", 3),
            Person("Samuel", "Andrewla", 3),
            Person("Sam", "Andrew", 50),
            Person("Samuel", "Drew", 67),
        )

        people = people.filter { it.age > 18 && it.lastName == "Andrew" }

        val rsItems = listOf(
            RssTile(
                "Yay, my first android application...",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                BlogType.TEXT
            ),
            RssTile(
                "Is this better than twitter ???",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                BlogType.TEXT
            ),
            RssTile(
                "Yay, watch me dance",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                BlogType.VIDEO,
                R.drawable.video_thumbnail
            ),
            RssTile(
                "I just uploaded a dance video, watch me twirl y'all.",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                BlogType.TEXT
            ),
            RssTile(
                "I look good, don't i.",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                BlogType.Photo,
                R.drawable.parrot
            ),
        )
        setContent {
            PracticeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    LazyColumn {
//                        items(people) {
//                            CardView(it) // Used this for default card view, like a container in react native. orr a view with card Tendencies.
//                        }
                        items(rsItems) {
                            when (it.type) {
                                BlogType.TEXT -> BlogViewText(it)
                                BlogType.VIDEO -> BlogViewVideo(it)
                                BlogType.Photo -> BlogViewPhoto(it)
                            }
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun CardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentDescription = "Photo of a person"
            )
            Column {
                Text(
                    text = person.firstName,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = person.lastName,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "Age: " + person.age,
                    modifier = Modifier.padding(0.dp)
                )
            }

        }

    }
}

@Composable
fun BlogViewText(rssItem: RssTile) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = rssItem.title,
            modifier = Modifier.padding(12.dp),
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black
        )

        Text(
            text = rssItem.content,
            modifier = Modifier.padding(12.dp),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun BlogViewVideo(rssItem: RssTile) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)

    ) {
        Text(
            text = rssItem.title,
            modifier = Modifier.padding(12.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )

        rssItem.mediaSrc?.let {
            Image(
                painter = painterResource(id = it),
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        Log.d("Image component","Random pressed")
                    }
                ,
                contentDescription = "Photo of a person"
            )
        }


    }
}


@Composable
fun BlogViewPhoto(it: RssTile) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        it.mediaSrc?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                modifier = Modifier
                    .fillMaxSize(),
                contentDescription = "Photo of a person"
            )
        }
        Text(
            text = it.title,
            modifier = Modifier.padding(10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
