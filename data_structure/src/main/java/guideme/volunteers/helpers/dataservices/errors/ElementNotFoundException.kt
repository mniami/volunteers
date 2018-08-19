package guideme.volunteers.helpers.dataservices.errors

class ElementNotFoundException(val item: Any, message: String) : Exception(message)