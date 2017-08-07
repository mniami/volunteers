package android.benchmark.domain

class SkillsTree {
    fun willingToTravel(skills : Skills) : Level {
        return skills.traveling * 0.5f * (1f / skills.introvert) * 0.3f * skills.humor * 0.2f
    }
}