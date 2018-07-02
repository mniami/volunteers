package guideme.volunteers.helpers.dataservices.errors

class ErrorException(errorMessage: ErrorMessage) : Exception(errorMessage.content)