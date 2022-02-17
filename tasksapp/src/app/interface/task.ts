import { Priority } from "../enum/priority.enum";
import { Status } from "../enum/status.enum";

export interface Task {
  id: number,
  number: number,
  title: string,
  owner: string,
  deadline: string,
  status: Status,
  priority: Priority
}