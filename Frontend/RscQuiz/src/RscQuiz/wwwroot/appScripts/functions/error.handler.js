"use strict";
function handleError(error) {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
}
exports.handleError = handleError;
//# sourceMappingURL=error.handler.js.map