package week8.q1_hackathon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a hackathon team registering under a specific track.
 */
public class Team {
    private final String name;
    private final List<Student> members;
    private final ScoringTrack track;
    private Project project;

    public Team(String name, ScoringTrack track) {
        this.name = name;
        this.track = track;
        this.members = new ArrayList<>();
    }

    public Team(String name, ScoringTrack track, List<Student> members) {
        this.name = name;
        this.track = track;
        this.members = new ArrayList<>(members);
    }

    public void addMember(Student student) {
        if (student != null && !members.contains(student)) {
            members.add(student);
        }
    }

    public String getName() {
        return name;
    }

    public ScoringTrack getTrack() {
        return track;
    }

    public List<Student> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
