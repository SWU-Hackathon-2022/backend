// import { rest } from "msw";

// export const baseURL = "http://49.50.163.18:8080";

// export const handlers = [
//   rest.get(`${baseURL}/music/0`, (req, res, ctx) => {
//     return res(
//       ctx.json({
//         responseTime: "2022-06-23 03:08:44",
//         isSuccess: true,
//         code: 1000,
//         message: "요청에 성공하였습니다.",
//         result: {
//           musicThumbnailUrl:
//             "https://woorkup.com/wp-content/uploads/2016/12/free-url-forwarding-2.png.webp",
//           musicFileUrl: null,
//           composerNickname: "김작곡닉네임",
//           composerThumbnailUrl:
//             "https://mblogthumb-phinf.pstatic.net/MjAyMDA4MTBfNjAg/MDAxNTk3MDY2ODcxODIx.izwOJKtLZxm-A2UGvmyOAy9bAeXO0GsKghVAQeRMl1Eg.8Vg1CSsX947p0WRZuE1HG2awuxKoPsffCHTNM1DbOYAg.JPEG.7wayjeju/DH-1109.jpg?type=w800",
//           musicIntro: "이것은 노래이름1 입니다!",
//           genre: "BALLAD",
//           hashTag: "#발라드 #신남",
//         },
//       })
//     );
//   }),

//   rest.post(`${baseURL}/login`, (req, res, ctx) => {
//     return res(
//       ctx.status(200),
//       ctx.json({
//         responseTime: "2022-05-14 19:23:39",
//         isSuccess: true,
//         code: 1000,
//         message: "요청에 성공하였습니다.",
//         result: {
//           dtype: "COMPOSER",
//         },
//       }),
//       {
//         loginId: "chominho96",
//         password: "abcde12345!",
//       }
//     );
//   }),

//   rest.post(`${baseURL}/login`, (req, res, ctx) => {
//     return res(
//       ctx.status(404),
//       ctx.json({
//         responseTime: "2022-05-14 19:24:04",
//         isSuccess: false,
//         code: 2010,
//         message: "유저 아이디 값을 확인해주세요.",
//       }),
//       {
//         loginId: "abcd",
//         password: "1234",
//       }
//     );
//   }),

//   rest.get(`${baseURL}/mypage`, (req, res, ctx) => {
//     return res(
//       ctx.status(200),
//       ctx.json({
//         responseTime: "2022-06-23 01:38:22",
//         isSuccess: true,
//         code: 1000,
//         message: "요청에 성공하였습니다.",
//         result: {
//           memberId: 1,
//           // member ID
//           nickName: "김작곡닉네임",
//           // member 닉네임
//           profileImgUrl: null,
//           // member 프로필 사진 URL
//           genreList: [
//             // 작곡가 주요 장르 리스트
//             "BALLAD",
//             "ROCK",
//           ],
//           musicResList: [
//             // 곡 리스트
//             {
//               musicThumbnailURL: null,
//               // 곡 썸네일 URL
//               musicName: "노래이름1",
//               // 곡 이름
//               genre: "BALLAD",
//               // 곡 장르
//             },
//             {
//               musicThumbnailURL: null,
//               musicName: "노래이름2",
//               genre: "POP",
//             },
//             {
//               musicThumbnailURL: null,
//               musicName: "노래이름3",
//               genre: "ROCK",
//             },
//           ],
//         },
//       })
//     );
//   }),
// ];
