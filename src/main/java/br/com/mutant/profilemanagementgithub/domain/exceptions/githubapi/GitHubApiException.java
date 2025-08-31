package br.com.mutant.profilemanagementgithub.domain.exceptions.githubapi;

public class GitHubApiException extends RuntimeException {
    public GitHubApiException() {
    }

    public GitHubApiException(String message) {
        super(message);
    }

    public GitHubApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public static GitHubApiException serviceUnavailable(Exception exception) {
        return new GitHubApiException(
                "External error: cannot connect to GitHub API",
                exception
        );
    }
}
