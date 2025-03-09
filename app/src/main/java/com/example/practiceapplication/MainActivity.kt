package com.example.practiceapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                "Welcome to android development",
                "Android development is not as bad as i thought, naa, for real, its actually not a bad stack if you deep it and go deep, i mean in a way  shaaaaaa.",
                "Post"
            )
        )
        setContent {
            PracticeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    LazyColumn {
                        items(people) {
                            CardView(it)
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
fun BlogView(rssItem: RssTile){}