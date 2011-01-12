/* Copyright 2011 Kindleit.net Software Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kindleit.gae;


/**
 * Run goal unbound to any phase. This goal is similar to run, except that it
 * does not require the package phase to have completed.
 *
 * @see RunGoal
 *
 * @author rhansen@kindleit.net
 * @goal execute
 * @requiresDependencyResolution runtime
 * @since 0.8.1
 */
public class ExecuteGoal extends RunGoal {

}
