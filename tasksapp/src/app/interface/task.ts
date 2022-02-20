import { Priority } from "../enum/priority.enum";
import { Status } from "../enum/status.enum";

export interface Task {
  id: number,
  title: string,
  owner: string,
  deadline: string,
  priority: Priority
  status: boolean,
}