import { Priority } from "../enum/priority.enum";

export interface Task {
  id: number,
  title: string,
  owner: string,
  deadline: string,
  status: number,
  priority: Priority
}