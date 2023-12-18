package com.example.infomaat2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutUs {
    // Description of your team or organization
    val description = """
        Welcome to OurTeam Kotlin! We are a passionate group of developers dedicated to creating innovative and 
        cutting-edge solutions using Kotlin programming language. Our journey began with a shared love for 
        technology and a mission to make a positive impact in the world through our coding expertise.
    """.trimIndent()

    // Vision and Mission statements
    val visionStatement = "Our Vision: To be pioneers in Kotlin development, pushing the boundaries of what's possible."

    val missionStatement = "Our Mission: To deliver high-quality, efficient, and scalable solutions that empower businesses and individuals."

    // Meet the Team
    val teamMembers = listOf(
        TeamMember("John Doe", "Co-founder & Lead Developer"),
        TeamMember("Jane Smith", "UX/UI Designer"),
        TeamMember("Alex Johnson", "Backend Developer"),
        // Add more team members as needed
    )

    // Team member data class
    data class TeamMember(val name: String, val role: String)
}

fun main() {
    // Instantiate the AboutUs class
    val aboutUs = AboutUs()

    // Print the content for your About Us page
    println(aboutUs.description)
    println(aboutUs.visionStatement)
    println(aboutUs.missionStatement)

    println("Meet the Team:")
    aboutUs.teamMembers.forEach { member ->
        println("${member.name} - ${member.role}")
    }
}

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}