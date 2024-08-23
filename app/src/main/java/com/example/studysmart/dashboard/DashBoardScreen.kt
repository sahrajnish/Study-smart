package com.example.studysmart.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.components.AddSubjectDialog
import com.example.studysmart.components.CountCard
import com.example.studysmart.components.SubjectCard
import com.example.studysmart.components.studySessionList
import com.example.studysmart.components.tasksList
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import javax.security.auth.SubjectDomainCombiner

@Composable
fun DashBoardScreen(modifier: Modifier = Modifier) {

    val subjects = listOf(
        Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0], subjectId = 0),
        Subject(name = "Maths", goalHours = 10f, colors = Subject.subjectCardColors[1], subjectId = 0),
        Subject(name = "Physics", goalHours = 10f, colors = Subject.subjectCardColors[2], subjectId = 0),
        Subject(name = "DSA", goalHours = 10f, colors = Subject.subjectCardColors[3], subjectId = 0),
        Subject(name = "Biology", goalHours = 10f, colors = Subject.subjectCardColors[4], subjectId = 0),
    )

    val tasks = listOf(
        Task(
            "HomeWork",
            "Maths Homework",
            1L,
            0,
            "Maths",
            false,
            taskSubjectId = 0,
            taskId = 4
        ),
        Task(
            "make notes",
            "Maths Homework",
            1L,
            2,
            "Maths",
            true,
            taskSubjectId = 0,
            taskId = 1
        ),
        Task(
            "practice problems",
            "Maths Homework",
            1L,
            3,
            "Maths",
            true,
            taskSubjectId = 0,
            taskId = 2
        ),
        Task(
            "study physics",
            "Maths Homework",
            1L,
            0,
            "physics",
            false,
            taskSubjectId = 0,
            taskId = 3
        ),
        Task(
            "study maths",
            "Maths Homework",
            1L,
            2,
            "physics",
            false,
            taskSubjectId = 0,
            taskId = 5
        )
    )

    val sessions: List<Session> = listOf(
        Session(
            sessionStudyId = 0,
            relatedToSubject = "English",
            date = 1L,
            duration = 2L,
            sessionId = 1
        ),
        Session(
            sessionStudyId = 1,
            relatedToSubject = "Maths",
            date = 1L,
            duration = 2L,
            sessionId = 2
        ),
        Session(
            sessionStudyId = 2,
            relatedToSubject = "Physics",
            date = 1L,
            duration = 2L,
            sessionId = 2
        ),
        Session(
            sessionStudyId = 3,
            relatedToSubject = "DSA",
            date = 1L,
            duration = 2L,
            sessionId = 3
        )
    )

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }

    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectedColor by remember {
        mutableStateOf(Subject.subjectCardColors.random())
    }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHour = goalHour,
        onSubjectNameChange = { subjectName = it },
        onGoalHourChange = { goalHour = it },
        selectedColors = selectedColor,
        onColorChange = { selectedColor = it },
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = { isAddSubjectDialogOpen = false }
    )

    Scaffold(
        topBar = { Title() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    subjectCount = 3,
                    studiedHour = "10",
                    goalHour = "15"
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects,
                    onAddIconClick = { isAddSubjectDialogOpen = true }
                )
            }
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }
            tasksList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming tasks. \n" +
                    "Click the + button in subject screen to add task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                sessions = sessions,
                emptyListText = "You don't have any recent study sessions. \n" +
                        "Start a study session to begin recording your progress.",
                onDeleteIconClick = {}
            )
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Title(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Study Smart",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier = Modifier,
    subjectCount: Int,
    studiedHour: String,
    goalHour: String
) {
    Row (modifier = modifier){
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHour
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHour
        )
    }
}

@Composable
private fun SubjectCardsSection(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects. \n Click + to add new subject.",
    onAddIconClick: () -> Unit
) {
    Column (
        modifier = modifier
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = onAddIconClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subjects"
                )
            }
        }
        if(subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.img_books),
                contentDescription = emptyListText
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList) { subject ->
                SubjectCard(
                    subjectName = subject.name,
                    gradientColor = subject.colors,
                    onClick = {}
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun prev() {
    DashBoardScreen()
}