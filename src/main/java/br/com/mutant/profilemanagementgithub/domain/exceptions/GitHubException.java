package br.com.mutant.profilemanagementgithub.domain.exceptions;

public class GitHubException extends RuntimeException {
    public GitHubException() {
    }

    public GitHubException(String message) {
        super(message);
    }

    public GitHubException(String message, Throwable cause) {
        super(message, cause);
    }

    public static GitHubException serviceUnavailable(Exception exception) {
        return new GitHubException(
                "External error: cannot connect to GitHub API",
                exception
        );
    }
}
