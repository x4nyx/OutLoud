# OutLoud
В качестве СУБД в проекте используется MySQL. Для работы с базой данных Разработан DAO-слой. Для вызова функций DAО-слоя используется слой сервисов.
Слой активирует все функциии в html файлах.
Вприложении реализован вход, регистрация и прослушивание музыки.

ENTITES(package entity):
User
Album
Group
Post
Track
Report
Genre



DAO-слой(package dao):
ALBUM DAO:
	findAllTracksInAlbum
	addTrackToAlbum
	deleteTrackFromAlbum
	isAlbumTrackListEmpty
	getTracksFromAlbum
	makeAlbum
	isExist
	findAllAlbums
	findAlbumById
	findAlbumByName
	deleteAlbumById
	updateAlbum
	deleteAllTracksFromAlbum
	updateTrackList
	createAlbum

GENRE DAO:
	makeGenre
	findAllGenres
	findGenreById
	deleteGenreById
	createGenre
	updateGenre
	
GROUP DAO:
	findMembersInGroup
	makeGroup
	findAllGroups
	findGroupById
	isExist
	deleteGroupById
	isGroupHasMembers
	deleteAllMembers
	updateGroupMembers
	deleteMemberFromGroup
	addMemberToGroup
	createGroup
	updateGroup
	
POST DAO:
	findAllPosts
	findPostById
	deletePostById
	isExist
	createPost
	updatePost
	
REPORT DAO:
	findAllReports
	findAllByStatus
	findReportById
	isExists
	deleteReportById
	createReport
	updateReport
	
TRACK DAO:
	makeTrack
	isExists
	findAllTracks
	findTrackById
	findTracksByName
	createTrack
	updateTrack
	deleteTrackById
	
USER DAO:
	isExist
	makeUser
	findAllUsers
	findUserById
	deleteUserById
	createUser
	updateUser

Слой сервисов(package service):
MainFilter: определяет Actions
MainServlt: Обрабатывает запросы

Action(package action):
Login
Register
Search

Front-end:
Используется технология jsp.
Пользователь может войти в аккаунтб зарегистрироваться. При нажатии на сердечко появляктся интерактивный плейлист.
Треки в плейлист загружаются с помощью JavaScript. Оформление реализовано с помощью CSS и технологии Bootstrap.


![photo_2023-06-02_16-36-18](https://github.com/x4nyx/OutLoud/assets/100283671/db720943-e787-4c08-9e30-4d2a5b0bbe11)
[Uml_диаграмма.docx](https://github.com/x4nyx/OutLoud/files/11635799/Uml_.docx)

