/*-
 * =LICENSE=
 * Kotlin Spark API: Examples
 * ----------
 * Copyright (C) 2019 - 2020 JetBrains
 * ----------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =LICENSEEND=
 */
package org.apache.spark.sql

import org.apache.spark.sql.catalyst.expressions.{AttributeReference, Expression}
import org.apache.spark.sql.types.{DataType, Metadata, StructField, StructType}


trait DataTypeWithClass {
  val dt: DataType
  val cls: Class[_]
  val nullable: Boolean
}

trait ComplexWrapper extends DataTypeWithClass

class KDataTypeWrapper(val dt: StructType
                       , val cls: Class[_]
                       , val nullable: Boolean = true) extends StructType with ComplexWrapper
case class KComplexTypeWrapper(dt: DataType, cls: Class[_], nullable: Boolean) extends DataType with ComplexWrapper {
  override def defaultSize: Int = dt.defaultSize

  override private[spark] def asNullable = dt.asNullable

}
case class KSimpleTypeWrapper(dt: DataType, cls: Class[_], nullable: Boolean) extends DataType with DataTypeWithClass {
  override def defaultSize: Int = dt.defaultSize

  override private[spark] def asNullable = dt.asNullable
}

object helpme {

  def listToSeq(i: java.util.List[_]): Seq[_] = Seq(i.toArray: _*)
}