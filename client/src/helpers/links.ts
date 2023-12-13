export const userIdPath = ":userId"
export const vacancyIdPath = ":vacancyId"

export const links = {
    login: "/login",
    registration: "/registration",
    logout: "/logout",

    home: "/",
    expandedPost: "/post/:postId",
    userPosts: `/user/:userId`,
    userPostsExpanded: "/user/:userId/post/:postId",

    userProfile: `/profile/:userId`,

    vacancies: "/vacancies",
    vacancyDetails: "/vacancies/:vacancyId",
    createVacancy: "/vacancy/create",
    candidatesForVacancy: "/vacancy/:vacancyId/candidates",

    candidates: "/candidates",
    candidate: `/candidate/:userId`
}