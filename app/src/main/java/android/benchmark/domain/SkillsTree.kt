package android.benchmark.domain

class SkillsTree {
    fun willingToTravel(skills : Skills) : Level {
        return skills.traveling * skills.introvert
    }
}