package br.com.mutant.profilemanagementgithub.domain.exceptions;

public class GitHubUserException extends RuntimeException {
    public GitHubUserException(String message) {
        super(message);
    }

    public static GitHubUserException requiredUserId() {
        return new GitHubUserException("User ID is required and cannot be null");
    }

    public static GitHubUserException cannotFindUser() {
        return new GitHubUserException("User ID is required and cannot be null");
    }
}
