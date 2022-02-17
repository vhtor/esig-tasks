import { Task } from "./task"

export interface CustomResponse {
  timeStamp: Date,
  statusCode: number,
  status: string,
  reason: string,
  message: string,
  devMessage: string,
  data: { tasks?: Task[], task?: Task };
}